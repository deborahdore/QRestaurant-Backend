package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.AttendanceMapper;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class AttendanceRepository {

    @Autowired
    private AttendanceMapper attendanceMapper;

    public Attendance getAttendance(Integer idAttendance) {
        return attendanceMapper.getAttendance(idAttendance);
    }

    public int postAttendance(Attendance attendance) {
        return attendanceMapper.postAttendance(attendance);
    }

    public int putAttendance(Attendance attendance) {
        return attendanceMapper.putAttendance(attendance);
    }

    public int deleteAttendance(Attendance attendance) {
        return attendanceMapper.deleteAttendance(attendance);
    }

    public int deleteAttendanceByID(Integer idAttendance) {
        return attendanceMapper.deleteAttendanceByID(idAttendance);
    }

    public List<Attendance> getClientAttendancesFrom(Integer idClient, Timestamp selectPassedDate) {
        return attendanceMapper.getClientAttendancesFrom(idClient, selectPassedDate);
    }

    public List<Attendance> getFrequentedSocieties(Integer idClientFK, Timestamp selectPassedDate) {
        return attendanceMapper.getFrequentedSocieties(idClientFK, selectPassedDate);
    }

    public List<Client> getClientListInSocietyFrom(Integer idSociety, Timestamp arrivalTime) {
        return attendanceMapper.getClientListInSocietyFrom(idSociety, arrivalTime);
    }

    public List<Attendance> getClientListInSocietyIn(Integer idSociety, Timestamp day) {
        return attendanceMapper.getAttendanceClientsInSocietyIn(idSociety, day);

    }

    public List<Attendance> getClientAttendances(Integer idClientFK) {
        return attendanceMapper.getClientAttendances(idClientFK);
    }

    public List<Attendance> getClientsAttendanceInSociety(Integer idSociety) {
        return attendanceMapper.getAttendanceClientsInSociety(idSociety);
    }
}
