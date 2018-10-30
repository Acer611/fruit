package com.dragon.fruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @see  项目启动类
 * @author  Gaofei
 * @Date  2018-10-30
 */
@EnableSwagger2
@SpringBootApplication
public class FruitApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(FruitApplication.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FruitApplication.class);
    }
}
