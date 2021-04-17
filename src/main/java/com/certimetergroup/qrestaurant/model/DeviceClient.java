package com.certimetergroup.qrestaurant.model;

import java.sql.Timestamp;

public class DeviceClient {
    private Integer idDeviceClient;
    private String uuid;
    private String registrationToken;
    private String QRCode;
    private String firebaseRegistrationToken;
    private Timestamp createAt;
    private Integer idClientFK;

    public DeviceClient() {
    }

    public DeviceClient(Integer idDeviceClient, String uuid, String registrationToken, String QRCode, Integer idClientFK) {
        this.idDeviceClient = idDeviceClient;
        this.uuid = uuid;
        this.registrationToken = registrationToken;
        this.QRCode = QRCode;
        this.idClientFK = idClientFK;
    }

    public DeviceClient(Integer idDeviceClient, String uuid, String registrationToken, String QRCode, String firebaseRegistrationToken, Integer idClientFK) {
        this.idDeviceClient = idDeviceClient;
        this.uuid = uuid;
        this.registrationToken = registrationToken;
        this.QRCode = QRCode;
        this.firebaseRegistrationToken = firebaseRegistrationToken;
        this.createAt = new Timestamp(System.currentTimeMillis());
        this.idClientFK = idClientFK;
    }

    public Integer getIdDeviceClient() {
        return idDeviceClient;
    }

    public void setIdDeviceClient(Integer idDeviceClient) {
        this.idDeviceClient = idDeviceClient;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getFirebaseRegistrationToken() {
        return firebaseRegistrationToken;
    }

    public void setFirebaseRegistrationToken(String firebaseRegistrationToken) {
        this.firebaseRegistrationToken = firebaseRegistrationToken;
        this.createAt = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Integer getIdClientFK() {
        return idClientFK;
    }

    public void setIdClientFK(Integer idClientFK) {
        this.idClientFK = idClientFK;
    }
}
