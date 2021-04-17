package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AttendanceBaseService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    /*** GET ***/
    public Attendance getAttendance(Integer idAttendance) {
        Attendance attendance = attendanceRepository.getAttendance(idAttendance);
        if (attendance == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return attendance;
    }

    /**
     * POST
     */
    public void insertAttendace(Attendance attendance) {
        int rowsInserted = attendanceRepository.postAttendance(attendance);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * PUT
     */
    public void updateAttendance(Attendance attendance) {
        int rowsUpdated = attendanceRepository.putAttendance(attendance);
        if (rowsUpdated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * DELETE
     */

    public void deleteAttendance(Attendance attendance) {
        int rowsDeleted = attendanceRepository.deleteAttendance(attendance);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void deleteAttendance(Integer idAttendance) {
        int rowsDeleted = attendanceRepository.deleteAttendanceByID(idAttendance);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }
}
