package com.dragon.fruit.config;
import com.alibaba.fastjson.JSONObject;
import com.dragon.fruit.dao.fruit.TokenDao;
import com.dragon.fruit.entity.po.fruit.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @program fruit
 * @description: 拦截器的配置
 * @author: Gaofei
 * @create: 2018/11/07 16:24
 */
@Component
public class InterceptorConfig implements HandlerInterceptor {
    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 进入controller层之前拦截请求
     * @param httpServletRequest
     * @param httpServletResponse
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object obj) throws Exception {

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

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        String token =  httpServletRequest.getHeader("token");
        if(null==token){
            PrintWriter printWriter = httpServletResponse.getWriter();
            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","您没有登录，请登录!!");
            res.put("code","5001");
            printWriter.append(res.toString());

            //printWriter.write("{retCode:5001,retMsg:\"You are offline. Please login again!!\"}");
            return false;
        }

        String userJson = stringRedisTemplate.opsForValue().get("User_GetUserByToken:" + token);
        //TokenEntity tokenEntity = tokenDao.queryTokenByToken(token);
        if(null!=userJson){
            return true;
        }else{
            PrintWriter printWriter = httpServletResponse.getWriter();
            JSONObject res = new JSONObject();
            res.put("success","false");
            res.put("msg","您已下线，请重新登录!!");
            res.put("code","5002");
            printWriter.append(res.toString());
            return false;
        }
     /*  return  true;*/


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
       // httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        System.out.println("---------------视图渲染之后的操作-------------------------0");
    }


}
