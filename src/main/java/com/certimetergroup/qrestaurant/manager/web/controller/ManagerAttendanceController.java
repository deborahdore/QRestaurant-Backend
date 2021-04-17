package com.certimetergroup.qrestaurant.manager.web.controller;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.response.GetClientsAttendaceResponse;
import com.certimetergroup.qrestaurant.manager.response.GetClientsAttendanceInDateResponse;
import com.certimetergroup.qrestaurant.manager.service.AttendanceService;
import com.certimetergroup.qrestaurant.manager.service.ManagerJwtService;
import com.certimetergroup.qrestaurant.manager.service.NotificationService;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.model.Notification;
import com.certimetergroup.qrestaurant.response.Response;
import com.certimetergroup.qrestaurant.service.ExportService;
import com.certimetergroup.qrestaurant.utility.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Validated
@RestController
public class ManagerAttendanceController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private ManagerJwtService jwtService;
    @Autowired
    private ExportService exportService;

    /*
     * ENDPOINT USED TO GET THE LIST OF CLIENT THAT WHERE IN THE SAME RESTAURANT OF THE INFECTED CLIENT
     * @param accessToken is manager's JWT access token
     * @param idNotification is the id of the notification that the manager received from the positive client
     * @return list of client
     */
    @GetMapping(path = "/attendance/infected/notification/{idNotification}", produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FileSystemResource> getClientsAttendanceFromNotification(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                                                   @PathVariable @NotNull Integer idNotification) {
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        Notification notification = notificationService.getExistingNotification(idNotification, idManager);
        Attendance attendance = attendanceService.getAttendance(notification.getIdAttendanceFK());

        Integer idSociety = attendance.getIdSocietyFK();
        Timestamp arrivalTime = attendance.getArrivalTime();
        List<Client> clientList = attendanceService.getClientListInSocietyFrom(idSociety, arrivalTime);
        FileSystemResource file = exportService.exportCSVForClientInfected(clientList, idSociety, idNotification, arrivalTime);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=" + file.getFilename())
                .body(file);

    }

    /*
     * ENDPOINT USED TO GET THE ATTENDANCES FOR A SPECIFIED SOCIETY IN A SPECIFIC DATE
     * @param accessToken is manager's JWT access token
     * @param idSociety is the society' id of which the manager wants the attendances
     * @param datetime is the date of which the manager wants the attendances, format "yyyy-MM-dd"
     * @return list of clients
     */
    @GetMapping("/attendance/society/{idSociety}/date/{datetime}")
    public ResponseEntity<Response> getAttendanceForDate(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                         @PathVariable @NotNull Integer idSociety,
                                                         @PathVariable @NotEmpty String datetime) {
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        Map<Client, Timestamp> clients = attendanceService.getClientListInSocietyIn(idSociety, idManager, DateUtility.formatToTimestamp(datetime));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetClientsAttendanceInDateResponse(ResponseType.SUCCESS, clients));
    }

    /*
     * ENDPOINT USED TO GET THE ATTENDANCES FOR A SPECIFIED SOCIETY
     * @param accessToken is manager's JWT access token
     * @param idSociety is the society' id of which the manager wants the attendances
     * @param datetime is the date of which the manager wants the attendances, format "yyyy-MM-dd"
     * @return list of clients
     */
    @GetMapping("/attendance/society/{idSociety}")
    public ResponseEntity<Response> getAttendance(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                  @PathVariable @NotNull Integer idSociety) {
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        List<Attendance> attendances = attendanceService.getClientListInSociety(idSociety, idManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetClientsAttendaceResponse(ResponseType.SUCCESS, attendances));
    }
}
