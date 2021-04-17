package com.certimetergroup.qrestaurant.manager.web.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@ComponentScan(basePackages = {
        "com.certimetergroup.qrestaurant.manager.web.configuration",
        "com.certimetergroup.qrestaurant.manager.service",
        "com.certimetergroup.qrestaurant.manager.service",
        "com.certimetergroup.qrestaurant.manager.web.controller",
})
@EnableWebMvc
public class ApiWebManagerServletConfiguration {
}
