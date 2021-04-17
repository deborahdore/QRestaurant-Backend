package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.repository.AttendanceRepository;
import com.certimetergroup.qrestaurant.service.AttendanceBaseService;
import com.certimetergroup.qrestaurant.utility.RegistrationTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private DeviceClientService deviceClientService;
    @Autowired
    private ClientService clientDeviceService;
    @Autowired
    private Manager2SocietyService manager2SocietyService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private SocietyService societyService;

    public List<Client> getClientListInSocietyFrom(Integer idSociety, Timestamp arrivalTime) {
        return attendanceRepository.getClientListInSocietyFrom(idSociety, arrivalTime);
    }

    public Map<Client, Timestamp> getClientListInSocietyIn(Integer idSociety, Integer idManager, Timestamp day) {
        if (!manager2SocietyService.isManagerSociety(idManager, idSociety))
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        Map<Client, Timestamp> map = new HashMap<>();
        List<Attendance> attendances = attendanceRepository.getClientListInSocietyIn(idSociety, day);
        attendances.forEach(attendance -> map.put(clientDeviceService.getClient(attendance.getIdClientFK()), attendance.getArrivalTime()));
        return map;
    }

    public void insertAttendance(String qrocde, Integer idSociety, Integer idManager) {
        if (!manager2SocietyService.isManagerSociety(idManager, idSociety))
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        DeviceClient deviceClient = deviceClientService.getDeviceClient(qrocde);
        if (deviceClient == null)
            throw new FailureException(ResponseType.INVALID_QRCODE, HttpStatus.UNAUTHORIZED);
        Attendance attendance = new Attendance(0, idSociety, deviceClient.getIdClientFK());
        this.insertAttendace(attendance);
        this.updateQRCODE(deviceClient, societyService.getSociety(idSociety).getSocietyName());
    }

    private void updateQRCODE(DeviceClient deviceClient, String societyName) {
        String newQRCode = RegistrationTokenUtility.generateQRCode(deviceClient.getUuid());
        deviceClient.setQRCode(newQRCode);
        deviceClientService.updateDevice(deviceClient);
        notificationService.sendNotificationQrCodeChanged(deviceClient, societyName);
    }

    public List<Attendance> getClientListInSociety(Integer idSociety, Integer idManager) {
        if (!manager2SocietyService.isManagerSociety(idManager, idSociety))
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return attendanceRepository.getClientsAttendanceInSociety(idSociety);
    }
}