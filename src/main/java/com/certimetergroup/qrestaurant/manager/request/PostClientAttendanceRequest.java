package com.certimetergroup.qrestaurant.manager.request;

import javax.validation.constraints.NotEmpty;

public class PostClientAttendanceRequest {
    @NotEmpty String qrcode;

    public PostClientAttendanceRequest() {
    }

    public PostClientAttendanceRequest(@NotEmpty String qrcode) {
        this.qrcode = qrcode;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}

