package com.yuge.ing.spring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: yuge
 * @date: 2024/10/11
 **/
@Slf4j
@Aspect
@Component
public class MyAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller) || @annotation(org.springframework.stereotype.Controller)")
    public void controllerAspect() {
    }

    @Before(value = "controllerAspect()")
    public void before(JoinPoint joinPoint) {
        // 可获取到Controller方法上的参数
        joinPoint.getArgs();
        log.debug("MyAspect#before");
    }

    @Around(value = "controllerAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.getArgs();
        log.debug("MyAspect#around");
        // 需要动态修改方法参数
        // 根据注解处理joinPoint.getArgs()
        return joinPoint.proceed(joinPoint.getArgs());
        // 不需要修改方法参数
//        return joinPoint.proceed();
    }

    @After(value = "controllerAspect()")
    public void after() {
        log.debug("MyAspect#after");
    }

}
