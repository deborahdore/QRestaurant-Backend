package com.certimetergroup.qrestaurant.security.exception;

import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import org.springframework.http.HttpStatus;

public class AuthenticationErrException extends RuntimeException {
    private final AuthenticationErrType authenticationErrType;
    private final HttpStatus httpStatus;

    public AuthenticationErrException(AuthenticationErrType authenticationErrType, HttpStatus httpStatus) {
        this.authenticationErrType = authenticationErrType;
        this.httpStatus = httpStatus;
    }

    public AuthenticationErrType getType() {
        return authenticationErrType;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }
}
