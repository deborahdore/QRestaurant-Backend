package com.certimetergroup.qrestaurant.client.response;

import com.certimetergroup.qrestaurant.dto.DTOAttendanceSociety;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAttendanceResponse extends Response {
    private int numAttendances;
    private List<DTOAttendanceSociety> attendances;

    public GetAttendanceResponse(ResponseType response, Map<Attendance, Society> attendance2society) {
        super(response);
        attendances = new ArrayList<>();
        attendance2society.forEach((attendance, society) -> {
            DTOAttendanceSociety dtoAttendanceSociety = attendance.toDTOAttendanceSociety();
            dtoAttendanceSociety.setSociety(society.toDTO());
            attendances.add(dtoAttendanceSociety);
        });
        this.numAttendances = attendances.size();
    }

    public int getNumAttendances() {
        return numAttendances;
    }

    public void setNumAttendances(int numAttendances) {
        this.numAttendances = numAttendances;
    }

    public List<DTOAttendanceSociety> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<DTOAttendanceSociety> attendances) {
        this.attendances = attendances;
    }
}