package com.certimetergroup.qrestaurant.dto;

import java.sql.Timestamp;

public class DTONotification {
    Integer idNotification;
    String message;
    Timestamp datetime;
    DTOAttendance attendance;


    public DTONotification() {
    }

    public DTONotification(Integer idNotification, String message, Timestamp datetime, DTOAttendance attendance) {
        this.idNotification = idNotification;
        this.message = message;
        this.datetime = datetime;
        this.attendance = attendance;
    }

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public DTOAttendance getAttendance() {
        return attendance;
    }

    public void setAttendance(DTOAttendance attendance) {
        this.attendance = attendance;
    }
}
