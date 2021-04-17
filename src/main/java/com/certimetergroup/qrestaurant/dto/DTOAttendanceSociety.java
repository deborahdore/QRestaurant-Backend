package com.certimetergroup.qrestaurant.dto;

import com.certimetergroup.qrestaurant.model.Attendance;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

public class DTOAttendanceSociety extends DTOAttendance {
    DTOSociety society;

    public DTOAttendanceSociety(Integer idAttendance, Timestamp arrivalTime, DTOSociety society) {
        super(idAttendance, arrivalTime);
        this.society = society;
    }

    public DTOAttendanceSociety() {
    }

    public DTOSociety getSociety() {
        return society;
    }

    public void setSociety(DTOSociety society) {
        this.society = society;
    }

    public Attendance toAttendance() {
        Attendance attendance = new Attendance();
        BeanUtils.copyProperties(this, attendance);
        return attendance;
    }

    @Override
    public String toString() {
        return "DTOAttendanceSociety{" +
                "society=" + society +
                ", idAttendance=" + idAttendance +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
