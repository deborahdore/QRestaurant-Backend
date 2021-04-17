package com.certimetergroup.qrestaurant.model;

import com.certimetergroup.qrestaurant.dto.DTOAttendance;
import com.certimetergroup.qrestaurant.dto.DTOAttendanceSociety;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

public class Attendance {
    private Integer idAttendance;
    private Timestamp arrivalTime;
    private Integer idSocietyFK;
    private Integer idClientFK;

    public Attendance(Integer idAttendance, Integer idSocietyFK, Integer idClientFK) {
        this.idAttendance = idAttendance;
        this.arrivalTime = new Timestamp(System.currentTimeMillis());
        this.idSocietyFK = idSocietyFK;
        this.idClientFK = idClientFK;
    }

    public Attendance(Integer idAttendance, Timestamp arrivalTime, Integer idSocietyFK, Integer idClientFK) {
        this.idAttendance = idAttendance;
        this.arrivalTime = arrivalTime;
        this.idSocietyFK = idSocietyFK;
        this.idClientFK = idClientFK;
    }

    public Attendance() {
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

    public Integer getIdSocietyFK() {
        return idSocietyFK;
    }

    public void setIdSocietyFK(Integer idSocietyFK) {
        this.idSocietyFK = idSocietyFK;
    }

    public Integer getIdClientFK() {
        return idClientFK;
    }

    public void setIdClientFK(Integer idClientFK) {
        this.idClientFK = idClientFK;
    }

    public DTOAttendanceSociety toDTOAttendanceSociety() {
        DTOAttendanceSociety dtoAttendanceSociety = new DTOAttendanceSociety();
        BeanUtils.copyProperties(this, dtoAttendanceSociety);
        return dtoAttendanceSociety;
    }

    public DTOAttendance toDTO() {
        DTOAttendance dtoAttendance = new DTOAttendance();
        BeanUtils.copyProperties(this, dtoAttendance);
        return dtoAttendance;
    }
}
