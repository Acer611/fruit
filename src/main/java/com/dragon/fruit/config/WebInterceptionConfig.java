package com.dragon.fruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program fruit
 * @description: 注册拦截器
 * @author: apple
 * @create: 2018/11/07 16:34
 */

@Configuration
public class WebInterceptionConfig extends WebMvcConfigurationSupport {




    @Bean
    public InterceptorConfig interceptorConfig() {
        return new InterceptorConfig();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("进入拦截器.......");
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(interceptorConfig()).addPathPatterns("/api/**").excludePathPatterns("/tools/**");
    }
}

