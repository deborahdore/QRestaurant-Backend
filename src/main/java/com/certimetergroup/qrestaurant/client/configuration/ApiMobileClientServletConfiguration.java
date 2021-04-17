package com.certimetergroup.qrestaurant.client.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@ComponentScan(basePackages = {
        "com.certimetergroup.qrestaurant.client.configuration",
        "com.certimetergroup.qrestaurant.client.service",
        "com.certimetergroup.qrestaurant.client.controller",
})
@EnableWebMvc
public class ApiMobileClientServletConfiguration {

}
