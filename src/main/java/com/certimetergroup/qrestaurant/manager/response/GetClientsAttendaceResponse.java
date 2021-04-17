package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOAttendance;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class GetClientsAttendaceResponse extends Response {
    List<DTOAttendance> attendance;
    Integer numAttendances;

    public GetClientsAttendaceResponse(ResponseType response, List<Attendance> attendances) {
        super(response);
        this.attendance = attendances.stream().map(Attendance::toDTO).collect(Collectors.toList());
        this.numAttendances = this.attendance.size();
    }

    public List<DTOAttendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance.stream().map(Attendance::toDTO).collect(Collectors.toList());
    }

    public Integer getNumAttendances() {
        return numAttendances;
    }

    public void setNumAttendances(Integer numAttendances) {
        this.numAttendances = numAttendances;
    }
}
