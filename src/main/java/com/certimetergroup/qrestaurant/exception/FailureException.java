package com.certimetergroup.qrestaurant.exception;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import org.springframework.http.HttpStatus;

public class FailureException extends RuntimeException {
    private final ResponseType responseType;
    private final HttpStatus status;

    public FailureException(ResponseType responseType, HttpStatus status) {
        super(responseType.getResponseMessage());
        this.responseType = responseType;
        this.status = status;
    }

    public ResponseType getType() {
        return responseType;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
