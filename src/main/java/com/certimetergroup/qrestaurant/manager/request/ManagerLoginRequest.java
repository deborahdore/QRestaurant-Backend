package com.certimetergroup.qrestaurant.manager.request;

import javax.validation.constraints.NotEmpty;

public class ManagerLoginRequest {
    @NotEmpty
    private String phone;
    @NotEmpty
    private String password;
    @NotEmpty
    private String uuid;

    public ManagerLoginRequest(@NotEmpty String phone, @NotEmpty String password, @NotEmpty String uuid) {
        this.phone = phone;
        this.password = password;
        this.uuid = uuid;
    }

    public ManagerLoginRequest() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
