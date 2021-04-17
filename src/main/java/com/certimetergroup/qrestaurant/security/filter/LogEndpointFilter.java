package com.certimetergroup.qrestaurant.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogEndpointFilter extends OncePerRequestFilter {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String endpoint = request.getRequestURI();
        String httpMethod = request.getMethod();

        LOG.info("START REQUEST:");
        LOG.info("requestUri: {} --- httpMethod: {}", endpoint, httpMethod);
        chain.doFilter(request, response);
        LOG.info("END REQUEST:");
        LOG.info("requestUri: {} --- httpMethod: {}", endpoint, httpMethod);
    }
}
