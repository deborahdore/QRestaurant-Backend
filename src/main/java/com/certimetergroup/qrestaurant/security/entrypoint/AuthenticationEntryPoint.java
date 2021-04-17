package com.certimetergroup.qrestaurant.security.entrypoint;

import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.security.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Object response_type = request.getAttribute("response_type");
        Object http_status = request.getAttribute("http_status");
        AuthenticationErrType responseCodeEnum = null;
        if (response_type == null || response_type.getClass() != AuthenticationErrType.class || http_status == null || http_status.getClass() != HttpStatus.class) {
            responseCodeEnum = AuthenticationErrType.AUTHENTICATION_ERROR;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            responseCodeEnum = (AuthenticationErrType) response_type;
            response.setStatus(((HttpStatus) http_status).value());
        }
        Response messageResponse = new Response(responseCodeEnum);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(new ObjectMapper().writeValueAsString(messageResponse));
    }
}
