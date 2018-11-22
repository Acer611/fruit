package com.dragon.fruit.config;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program fruit
 * @description:
 * @author: Gaofei
 * @create: 2018/11/21 11:12
 */


@Component
@Order (2)
public class CORSFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Origin, X-Requested-With, X-Auth-Token");
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
        response.addHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(request, response);
    }
}

