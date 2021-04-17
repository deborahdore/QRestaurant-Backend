package com.certimetergroup.qrestaurant.model;

import java.sql.Timestamp;

public class DeviceManager {
    private Integer idDeviceManager;
    private String firebaseRegistrationToken;
    private Timestamp createAt;
    private String refreshToken;
    private String uuid;
    private String operatingSystem;
    private Integer IdManagerFK;

    public DeviceManager(Integer idDeviceManager, String firebaseRegistrationToken, Timestamp createAt, String refreshToken, String uuid, String operatingSystem, Integer idManagerFK) {
        this.idDeviceManager = idDeviceManager;
        this.firebaseRegistrationToken = firebaseRegistrationToken;
        this.createAt = createAt;
        this.refreshToken = refreshToken;
        this.uuid = uuid;
        this.operatingSystem = operatingSystem;
        IdManagerFK = idManagerFK;
    }

    public Integer getIdDeviceManager() {
        return idDeviceManager;
    }

    public void setIdDeviceManager(Integer idDeviceManager) {
        this.idDeviceManager = idDeviceManager;
    }

    public String getFirebaseRegistrationToken() {
        return firebaseRegistrationToken;
    }

    public void setFirebaseRegistrationToken(String firebaseRegistrationToken) {
        this.firebaseRegistrationToken = firebaseRegistrationToken;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Integer getIdManagerFK() {
        return IdManagerFK;
    }

    public void setIdManagerFK(Integer idManagerFK) {
        IdManagerFK = idManagerFK;
    }
}
