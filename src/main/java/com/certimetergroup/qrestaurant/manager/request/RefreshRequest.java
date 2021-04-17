package com.certimetergroup.qrestaurant.manager.request;

import javax.validation.constraints.NotEmpty;

public class RefreshRequest {
    @NotEmpty
    private String refreshToken;

    public RefreshRequest() {
    }


    public RefreshRequest(@NotEmpty String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
