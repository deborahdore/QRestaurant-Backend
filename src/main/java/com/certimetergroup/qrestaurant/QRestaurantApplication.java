package com.certimetergroup.qrestaurant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
        "com.certimetergroup.qrestaurant.configuration",
        "com.certimetergroup.qrestaurant.service",
        "com.certimetergroup.qrestaurant.repository",
        "com.certimetergroup.qrestaurant.security"

})
@MapperScan("com.certimetergroup.qrestaurant.mapper")
@SpringBootApplication
public class QRestaurantApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QRestaurantApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QRestaurantApplication.class);
    }
}
