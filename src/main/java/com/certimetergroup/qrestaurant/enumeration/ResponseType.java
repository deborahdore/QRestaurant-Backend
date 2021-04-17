package com.certimetergroup.qrestaurant.enumeration;

public enum ResponseType {

    /*** COMMON ***/
    SUCCESS(0, "SUCCESS"),
    UNEXPECTED_ERROR(1, "ERROR"),

    /*** JWT ERROR ***/
    INVALID_ACCESS_TOKEN(101, "INVALID ACCESS TOKEN"),
    MALFORMED_ACCESS_TOKEN(102, "MALFORMED ACCESS TOKEN"),
    EXPIRED_ACCESS_TOKEN(103, "EXPIRED ACCESS TOKEN"),
    INVALID_REFRESH_TOKEN(104, "INVALID REFRESH TOKEN"),
    MALFORMED_REFRESH_TOKEN(105, "MALFORMED REFRESH TOKEN"),
    ACCESS_TOKEN_STILL_VALID(106, "ACCESS TOKEN IS STILL VALID"),

    /*** REGISTRATION ERROR ***/
    INVALID_REGISTRATION(201, "INVALID REGISTRATION"),

    /*** LOGIN ERROR ***/
    INVALID_LOGIN(301, "INVALID LOGIN"),

    /*** REGISTRATION TOKEN ERROR ***/
    INVALID_REGISTRATION_TOKEN(401, "INVALID REGISTRATION TOKEN"),

    /*** FIREBASE ERRORS ***/
    FIREBASE_TOKEN_ERROR(501, "FIREBASE TOKEN ERROR"),

    /**
     * EXPORT ERROR
     **/
    EXPORT_ERROR(601, "EXPORT FAILURE"),

    /**
     * CLIENT ERROR
     **/
    INVALID_QRCODE(701, "INVALID QRCODE");


    /*** response type ***/
    private final Integer responseCode;
    private final String responseMessage;

    ResponseType(Integer responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
