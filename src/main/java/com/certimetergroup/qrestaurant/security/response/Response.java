package com.certimetergroup.qrestaurant.security.response;

import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.utility.PathUtility;

import java.sql.Timestamp;

public class Response {
    private final int code;
    private final String message;
    private final Timestamp timestamp;
    private final String path;

    public Response(AuthenticationErrType response) {
        this.code = response.getCode();
        this.message = response.getMessage();
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.path = PathUtility.getCurrentPath();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}

