package com.certimetergroup.qrestaurant.client.response;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.response.Response;

public class RegistrationResponse extends Response {
    private final String registrationToken;

    public RegistrationResponse(ResponseType response, String registrationToken) {
        super(response);
        this.registrationToken = registrationToken;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }
}
