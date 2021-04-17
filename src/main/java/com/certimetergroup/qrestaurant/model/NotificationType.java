package com.certimetergroup.qrestaurant.model;

public class NotificationType {
    private Integer idNotificationType;
    private String type;

    public NotificationType(Integer idNotificationType, String type) {
        this.idNotificationType = idNotificationType;
        this.type = type;
    }

    public Integer getIdNotificationType() {
        return idNotificationType;
    }

    public void setIdNotificationType(Integer idNotificationType) {
        this.idNotificationType = idNotificationType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
