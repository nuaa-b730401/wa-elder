package org.nuaa.wa.waelder.aop;

import com.google.common.base.Preconditions;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nuaa.wa.waelder.entity.Response;
import org.nuaa.wa.waelder.util.RequestUtils;
import org.nuaa.wa.waelder.util.constant.LogLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Pointcut("@annotation(org.nuaa.wa.waelder.aop.ServiceLog)")
    public void controllerAspect() {}

    @Around("controllerAspect()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = null ;
        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long beginTime = System.currentTimeMillis();
        proceed = joinPoint.proceed();
        Response response = (Response) proceed;

        String ip = RequestUtils.getRequestIp(request);
        String os = RequestUtils.getOs(request);
        String browser = RequestUtils.getBrowser(request);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ServiceLog serviceLog = signature.getMethod().getAnnotation(ServiceLog.class);
        Preconditions.checkNotNull(serviceLog, "annotation service log miss");

        String userId = "anon user";

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
