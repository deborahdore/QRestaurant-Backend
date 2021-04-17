package com.certimetergroup.qrestaurant.client.controller;

import com.certimetergroup.qrestaurant.client.response.GetAttendanceResponse;
import com.certimetergroup.qrestaurant.client.service.AttendanceService;
import com.certimetergroup.qrestaurant.client.service.DeviceClientService;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Validated
@RestController
public class ClientAttendanceController {

    @Autowired
    private DeviceClientService deviceClientService;
    @Autowired
    private AttendanceService attendanceService;

    /*
     * ENDPOINT USED TO GET CLIENT'S ATTENDANCE
     * @param registrationToken is client's registration token
     * @return list of attendances containing the society
     */
    @GetMapping("/attendance")
    public ResponseEntity<Response> getAttendances(@RequestHeader("Authorization-Client-Token") @NotEmpty String registrationToken) {
        DeviceClient deviceClient = deviceClientService.getDeviceClientByRegistrationToken(registrationToken);
        Map<Attendance, Society> mapAttendanceSociety = attendanceService.getClientAttendances(deviceClient.getIdClientFK());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetAttendanceResponse(ResponseType.SUCCESS, mapAttendanceSociety));
    }
}
