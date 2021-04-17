package com.certimetergroup.qrestaurant.manager.mobile.controller;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.request.PostClientAttendanceRequest;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=" + file.getFilename())
                .body(file);

    }

    /*
     * ENDPOINT USED TO POST AN ATTENDANCE FOR A SPECIFIC SOCIETY
     * @param accessToken is manager's JWT access token
     * @param idSociety is the society' id for which the attendance must be registered
     * @param postClientAttendanceRequest contains the qrcode of the client whom attendance must be registered
     * @return success
     */
    @PostMapping(value = "/attendance/society/{idSociety}")
    public ResponseEntity<Response> postAttendance(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                   @PathVariable @NotNull Integer idSociety,
                                                   @RequestBody @Valid PostClientAttendanceRequest postClientAttendanceRequest) {
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        String qrcode = postClientAttendanceRequest.getQrcode();
        attendanceService.insertAttendance(qrcode, idSociety, idManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ResponseType.SUCCESS));
    }

    /*
     * ENDPOINT USED TO GET THE ATTENDANCES FOR A SPECIFIED SOCIETY IN A SPECIFIC DATE
     * @param accessToken is manager's JWT access token
     * @param idSociety is the society' id of which the manager wants the attendances
     * @param datetime is the date of which the manager wants the attendances, format yyyy-MM-dd
     * @return list of clients
     */
    @GetMapping("/attendance/society/{idSociety}/date/{date}")
    public ResponseEntity<Response> getAttendance(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                  @PathVariable @NotNull Integer idSociety,
                                                  @PathVariable @NotEmpty String date) {
        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        Map<Client, Timestamp> clients = attendanceService.getClientListInSocietyIn(idSociety, idManager, DateUtility.formatToTimestamp(date));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetClientsAttendanceInDateResponse(ResponseType.SUCCESS, clients));
    }
}
