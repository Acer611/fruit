
package com.dragon.fruit.config;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * web层访问日志切面
 * 此类主要有两个研究目的
 * 一是如何在Spring Boot中引入Aop功能，二是如何使用Aop做切面去统一处理Web请求的日志。
 * @author  Gaofei
 * @Date 2018-11-07
 */



@Aspect
@Order(1)
@Component
public class WebAccessAspect {

    //记录日志
    private static final org.slf4j.Logger logger  = LoggerFactory.getLogger(WebAccessAspect.class);


    ThreadLocal<Long> startTime = new ThreadLocal<>();


   // 在Spring 2.0中，Pointcut的定义包括两个部分：Pointcut表示式(expression)和Pointcut签名(signature)
    //Pointcut表示式
    @Pointcut("execution(public * com.dragon.fruit.controller..*.*(..))")  //表示匹配com.style.slack.controller 包及其子包下的所有的公有方法
    //Point签名
    public void webLog(){}




   // @Pointcut("execution(* *com.style.slack.service.*(..))")//表示匹配com.style.slack.service包中所有的公有方法
    //public void serviceLog(){}


    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {//  通过JoinPoint 获取通知的签名信息，如目标方法名，目标方法参数信息等
        startTime.set(System.currentTimeMillis());
        System.out.println("我是前置通知!!!");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        System.out.println(signature.getName());
        //AOP代理类的名字
        System.out.println(signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        System.out.println("...............前置通知............");


        // 记录下请求内容
        logger.debug("URL : " + request.getRequestURL().toString());
        logger.debug("HTTP_METHOD : " + request.getMethod());
        logger.debug("IP : " + request.getRemoteAddr());
        logger.debug("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.debug("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.debug("RESPONSE : " + ret);
        logger.debug("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}

