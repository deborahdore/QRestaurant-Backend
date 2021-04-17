package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOManagerResponse;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.response.Response;


public class ManagerLoginRefreshResponse extends Response {
    private final String accessToken;
    private final String refreshToken;
    private final DTOManagerResponse manager;

    public ManagerLoginRefreshResponse(ResponseType response, String accessToken, String refreshToken, Manager manager) {
        super(response);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.manager = manager.toResponseDTO();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public DTOManagerResponse getManager() {
        return manager;
    }
}
