package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOManagerResponse;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.response.Response;

public class ManagerLoginResponse extends Response {
    private DTOManagerResponse manager;
    private String accessToken;
    private String refreshToken;

    public ManagerLoginResponse(ResponseType response, Manager manager, String accessToken, String refreshToken) {
        super(response);
        this.manager = manager.toResponseDTO();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public DTOManagerResponse getManager() {
        return manager;
    }

    public void setManager(DTOManagerResponse manager) {
        this.manager = manager;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
