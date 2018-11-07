package com.dragon.fruit.config;

import com.dragon.fruit.entity.po.fruit.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @program fruit
 * @description: 拦截器的配置
 * @author: Gaofei
 * @create: 2018/11/07 16:24
 */
@Component
public class InterceptorConfig implements HandlerInterceptor {




    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //TODO 处理拦截的请求
       /* HttpSession session = httpServletRequest.getSession();
        if(!StringUtils.isEmpty(session.getAttribute("userName"))){
            return true;
        }
        else{
            PrintWriter printWriter = httpServletResponse.getWriter();
            printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");
            return false;
        }*/
       System.out.println("--------------请求之前的处理操作---------------");

       return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("---------------视图渲染之后的操作-------------------------0");
    }


}
