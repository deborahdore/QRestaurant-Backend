package com.certimetergroup.qrestaurant.model;

import com.certimetergroup.qrestaurant.dto.DTONotification;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

public class Notification {
    private Integer idNotification;
    private String message;
    private Timestamp datetime;
    private Integer idNotificationTypeFK;
    private Integer idManagerFK;
    private Integer idAttendanceFK;

    public Notification(Integer idNotification, String message, Integer idNotificationTypeFK, Integer idManagerFK, Integer idAttendanceFK) {
        this.idNotification = idNotification;
        this.message = message;
        this.datetime = new Timestamp(System.currentTimeMillis());
        this.idNotificationTypeFK = idNotificationTypeFK;
        this.idManagerFK = idManagerFK;
        this.idAttendanceFK = idAttendanceFK;
    }

    public Notification() {
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

    public Integer getIdNotificationTypeFK() {
        return idNotificationTypeFK;
    }

    public void setIdNotificationTypeFK(Integer idNotificationTypeFK) {
        this.idNotificationTypeFK = idNotificationTypeFK;
    }

    public Integer getIdManagerFK() {
        return idManagerFK;
    }

    public void setIdManagerFK(Integer idManagerFK) {
        this.idManagerFK = idManagerFK;
    }

    public Integer getIdAttendanceFK() {
        return idAttendanceFK;
    }

    public void setIdAttendanceFK(Integer idAttendanceFK) {
        this.idAttendanceFK = idAttendanceFK;
    }

    public DTONotification toDTO() {
        DTONotification dtoNotification = new DTONotification();
        BeanUtils.copyProperties(this, dtoNotification);
        return dtoNotification;
    }
}
