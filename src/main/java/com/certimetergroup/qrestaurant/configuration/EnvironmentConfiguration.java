package com.certimetergroup.qrestaurant.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jmx.support.RegistrationPolicy;

import javax.annotation.PostConstruct;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "classpath:/${env}/database.properties", ignoreResourceNotFound = false),
        @PropertySource(value = "classpath:/${env}/firebase.properties", ignoreResourceNotFound = false),
        @PropertySource(value = "classpath:/${env}/jwt_token.properties", ignoreResourceNotFound = false),
        @PropertySource(value = "classpath:/${env}/export.properties", ignoreResourceNotFound = false),
        @PropertySource(value = "classpath:/${env}/notification.properties", ignoreResourceNotFound = false, encoding = "UTF-8"),
        @PropertySource(value = "classpath:/${env}/password_encoder.properties", ignoreResourceNotFound = false),


})
public class EnvironmentConfiguration {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${env}")
    private String env;

    @PostConstruct
    public void init() {
        logger.info("ENVIRONMENT => " + env);
        logger.debug("DEBUG");
    }
}