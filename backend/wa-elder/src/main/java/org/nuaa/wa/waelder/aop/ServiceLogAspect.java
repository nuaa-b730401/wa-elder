package org.nuaa.wa.waelder.aop;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.entity.SysLogEntity;
import org.nuaa.wa.waelder.service.AdminUserService;
import org.nuaa.wa.waelder.service.SysLogService;
import org.nuaa.wa.waelder.util.CookieUtil;
import org.nuaa.wa.waelder.util.RequestUtils;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.nuaa.wa.waelder.util.constant.PermissionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2019/3/3 15:27
 */
@Aspect
@Component
@Order(-5)
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogLevel.SYSTEM);

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private AdminUserService adminUserService;

    @Pointcut("@annotation(org.nuaa.wa.waelder.aop.ServiceLog)")
    public void controllerAspect() {}

    @Around("controllerAspect()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null ;
        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long beginTime = System.currentTimeMillis();
        proceed = joinPoint.proceed();

        String ip = RequestUtils.getRequestIp(request);
        String os = RequestUtils.getOs(request);
        String browser = RequestUtils.getBrowser(request);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServiceLog serviceLog = signature.getMethod().getAnnotation(ServiceLog.class);
        Preconditions.checkNotNull(serviceLog, "annotation service log miss");

        Cookie cookie = CookieUtil.getCookieByName(PermissionConstant.USER_INFO_HEADER, request);
        long userId = 0;
        if (cookie != null) {
            userId = Long.parseLong(cookie.getValue());
        } else {
            userId = 1;
        }
        SysLogEntity log = new SysLogEntity();
        log.setDescription(serviceLog.description());
        log.setTarget(serviceLog.name());
        log.setUserId(userId);
        log.setBrowser(browser);
        log.setOs(os);
        log.setIp(ip);
        log.setDuration(System.currentTimeMillis() - beginTime);

        sysLogService.addLog(log);

        if (!(proceed instanceof Response)) {
            logger.info("[{} {} {}] {} visit {}, duration={}",
                    ip, os, browser, userId, serviceLog.name(),
                    System.currentTimeMillis() - beginTime);
            return proceed;
        }

        Response response = (Response) proceed;
        logger.info("[{} {} {}] {} visit {}, status={}, duration={}",
                ip, os, browser, userId, serviceLog.name(), response.getCode(),
                System.currentTimeMillis() - beginTime);

        return response;
    }

    @AfterThrowing(pointcut = "controllerAspect()",throwing="e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable{
        logger.warn("exception : {}", e.getMessage());
    }
}
