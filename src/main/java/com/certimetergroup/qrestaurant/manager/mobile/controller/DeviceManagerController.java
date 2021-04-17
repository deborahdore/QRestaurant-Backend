package com.certimetergroup.qrestaurant.manager.mobile.controller;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.request.PatchFirebaseTokenRequest;
import com.certimetergroup.qrestaurant.manager.service.DeviceManagerService;
import com.certimetergroup.qrestaurant.manager.service.ManagerJwtService;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Validated
@RestController
public class DeviceManagerController {
    @Autowired
    private ManagerJwtService jwtService;
    @Autowired
    private DeviceManagerService deviceManagerService;

    /*
     * ENDPOINT USED TO PATCH MANAGER'S FIREBASE TOKEN
     * @param accessToken is manager's JWT access token
     * @return success
     */
    @PatchMapping("/device/firebase/registration/token")
    public ResponseEntity<Response> patchFirebaseRegistrationToken(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                                   @RequestBody @Valid PatchFirebaseTokenRequest request) {

        String uuid = jwtService.getFieldFromAccessToken(accessToken, "UUID", String.class);
        DeviceManager deviceManager = deviceManagerService.getDeviceManager(uuid);
        deviceManager.setFirebaseRegistrationToken(request.getFirebaseRegistrationToken());
        deviceManager.setCreateAt(new Timestamp(System.currentTimeMillis()));
        deviceManagerService.updateDeviceManager(deviceManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ResponseType.SUCCESS));
    }

    /*
     * ENDPOINT USED TO LOGOUT MANAGER
     * @param accessToken is manager's JWT access token
     * @return success
     */
    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestHeader("Authorization") @NotEmpty String accessToken) {

        String UUID = jwtService.getFieldFromAccessToken(accessToken, "UUID", String.class);
        deviceManagerService.deleteDeviceManager(UUID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ResponseType.SUCCESS));
    }

}
