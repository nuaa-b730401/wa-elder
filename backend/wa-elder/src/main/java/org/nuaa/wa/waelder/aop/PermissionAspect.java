package org.nuaa.wa.waelder.aop;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nuaa.wa.waelder.exception.PermissionException;
import org.nuaa.wa.waelder.util.CookieUtil;
import org.nuaa.wa.waelder.util.TokenGenerator;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.PermissionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Name: PermissionAspect
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-26 12:10
 * @Version: 1.0
 */
@Aspect
@Component
@Order(-4)
public class PermissionAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogLevel.SYSTEM);

    @Pointcut("@annotation(org.nuaa.wa.waelder.aop.Permission)")
    public void controllerAspect() {}

    @Before("controllerAspect()")
    public void check(JoinPoint joinPoint) throws Exception {
        Permission permission = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(Permission.class);
        Preconditions.checkNotNull(permission, "annotation permisson miss");

        String permissionValue = permission.value();

        if (PermissionConstant.PERMISSION_ANON.equals(permissionValue)) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            throw new PermissionException("has no permission");
        }

        Cookie targetCookie = CookieUtil.getCookieByName(PermissionConstant.TOKEN_HEADER, request);

        if (targetCookie == null || StringUtils.isEmpty(targetCookie.getValue())) {
            throw new PermissionException("has no permission");
        }

        String token = targetCookie.getValue();
        String userInfo = TokenGenerator.getInstance().decrypt(token);
        if (!userInfo.contains(".")) {
            throw new PermissionException("has no permission, invalid token " + token);
        }

        String[] parts = userInfo.split("\\.");
        if (parts.length != 2) {
            throw new PermissionException("has no permission, invalid token " + token);
        }

        if (PermissionConstant.PERMISSION_LOGIN.equals(permissionValue)) {
            // user login
        } else if (PermissionConstant.PERMISSION_ADMIN.equals(permissionValue)) {
            // admin login
        } else {
            // parse permission value
            String[] description = permissionValue.split(":");
            if (description.length != 3) {
                // TODO : 系统异常, 编码错误
            }

            String process = description[0];
            String target = description[1];
            String role = description[2];

            // TODO : check target permission
        }

        // TODO: check user

        long duration = Long.parseLong(parts[1]) - System.currentTimeMillis();
        if (duration > PermissionConstant.TOKEN_EXPIRE_TIME) {
            throw new PermissionException("has no permission, token expired " + token);
        }

        // refresh cookie
        targetCookie.setMaxAge(PermissionConstant.COOKIE_EXPIRE_TIME);
        Cookie userCookie = CookieUtil.getCookieByName(PermissionConstant.USER_INFO_HEADER, request);
        if (userCookie != null) {
            userCookie.setMaxAge(PermissionConstant.COOKIE_EXPIRE_TIME);
        }
    }

}
