package com.certimetergroup.qrestaurant.configuration;

import com.certimetergroup.qrestaurant.client.configuration.ApiMobileClientServletConfiguration;
import com.certimetergroup.qrestaurant.manager.mobile.configuration.ApiMobileManagerServletConfiguration;
import com.certimetergroup.qrestaurant.manager.web.configuration.ApiWebManagerServletConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ServletConfiguration {
    @Bean
    public ServletRegistrationBean<DispatcherServlet> apiMobileCLIENT() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApiMobileClientServletConfiguration.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet, "/client/mobile/*");
        servletRegistrationBean.setName("apiMobileCLIENT");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setMultipartConfig(new MultipartConfigElement("", 1024 * 1024 * 10, 1024 * 1024 * 30, 0));

        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> apiMobileMANAGER() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApiMobileManagerServletConfiguration.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet, "/manager/mobile/*");
        servletRegistrationBean.setName("apiMobileMANAGER");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setMultipartConfig(new MultipartConfigElement("", 1024 * 1024 * 10, 1024 * 1024 * 30, 0));
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean<DispatcherServlet> apiWebMANAGER() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApiWebManagerServletConfiguration.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean<DispatcherServlet> servletRegistrationBean = new ServletRegistrationBean<DispatcherServlet>(dispatcherServlet, "/manager/web/*");
        servletRegistrationBean.setName("apiWebMANAGER");
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.setMultipartConfig(new MultipartConfigElement("", 1024 * 1024 * 10, 1024 * 1024 * 30, 0));
        return servletRegistrationBean;
    }
}
