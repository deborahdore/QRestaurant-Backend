package com.certimetergroup.qrestaurant.security.enumeration;

public enum AuthenticationErrType {

    UNEXPECTED_ERROR(1, "GENERIC ERROR"),
    AUTHENTICATION_ERROR(3, "AUTHENTICATION ERROR"),
    /*** JWT ERROR ***/
    INVALID_ACCESS_TOKEN(101, "INVALID ACCESS TOKEN"),
    MALFORMED_ACCESS_TOKEN(102, "MALFORMED ACCESS TOKEN"),
    EXPIRED_ACCESS_TOKEN(103, "EXPIRED ACCESS TOKEN"),
    INVALID_REFRESH_TOKEN(104, "INVALID REFRESH TOKEN"),
    MALFORMED_REFRESH_TOKEN(105, "MALFORMED REFRESH TOKEN"),
    ACCESS_TOKEN_STILL_VALID(106, "ACCESS TOKEN IS STILL VALID"),

    /*** LOGIN ERROR ***/
    INVALID_LOGIN(301, "INVALID LOGIN"),

    /*** REGISTRATION TOKEN ERROR ***/
    INVALID_REGISTRATION_TOKEN(401, "INVALID REGISTRATION TOKEN");

    private int code;
    private String message;

    AuthenticationErrType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
