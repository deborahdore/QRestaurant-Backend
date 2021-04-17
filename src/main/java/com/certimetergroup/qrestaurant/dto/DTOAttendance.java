package com.certimetergroup.qrestaurant.dto;

import java.sql.Timestamp;

public class DTOAttendance {
    Integer idAttendance;
    Timestamp arrivalTime;

    public DTOAttendance() {
    }

    public DTOAttendance(Integer idAttendance, Timestamp arrivalTime) {
        this.idAttendance = idAttendance;
        this.arrivalTime = arrivalTime;
    }

    public Integer getIdAttendance() {
        return idAttendance;
    }

    public void setIdAttendance(Integer idAttendance) {
        this.idAttendance = idAttendance;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}
