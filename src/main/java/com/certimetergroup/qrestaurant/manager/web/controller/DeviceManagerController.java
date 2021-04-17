package com.certimetergroup.qrestaurant.manager.web.controller;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.service.DeviceManagerService;
import com.certimetergroup.qrestaurant.manager.service.ManagerJwtService;
import com.certimetergroup.qrestaurant.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@Validated
@RestController
public class DeviceManagerController {
    @Autowired
    private ManagerJwtService jwtService;
    @Autowired
    private DeviceManagerService deviceManagerService;

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
