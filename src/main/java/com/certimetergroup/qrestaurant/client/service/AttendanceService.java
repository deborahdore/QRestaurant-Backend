package com.certimetergroup.qrestaurant.client.service;

import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.repository.AttendanceRepository;
import com.certimetergroup.qrestaurant.service.AttendanceBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService extends AttendanceBaseService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SocietyService societyService;

    public Map<Attendance, Society> getClientAttendances(Integer idClientFK, Timestamp selectPassedDate) {
        Map<Attendance, Society> attendanceSocietyMap = new HashMap<>();
        List<Attendance> attendances = attendanceRepository.getFrequentedSocieties(idClientFK, selectPassedDate);
        attendances.forEach((attendance -> attendanceSocietyMap.put(attendance, societyService.getSociety(attendance.getIdSocietyFK()))));
        return attendanceSocietyMap;
    }

    public List<Attendance> getClientAttendancesFrom(Integer idClient, Timestamp selectPassedDate) {
        return attendanceRepository.getClientAttendancesFrom(idClient, selectPassedDate);
    }

    public Map<Attendance, Society> getClientAttendances(Integer idClientFK) {
        Map<Attendance, Society> attendanceSocietyMap = new HashMap<>();
        List<Attendance> attendances = attendanceRepository.getClientAttendances(idClientFK);
        attendances.forEach((attendance -> attendanceSocietyMap.put(attendance, societyService.getSociety(attendance.getIdSocietyFK()))));
        return attendanceSocietyMap;
    }
}
