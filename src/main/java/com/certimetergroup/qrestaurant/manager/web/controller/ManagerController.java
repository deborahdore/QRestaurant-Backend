package com.certimetergroup.qrestaurant.manager.web.controller;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.request.ManagerLoginRequest;
import com.certimetergroup.qrestaurant.manager.request.ManagerRegistrationRequest;
import com.certimetergroup.qrestaurant.manager.request.RefreshRequest;
import com.certimetergroup.qrestaurant.manager.response.ManagerLoginRefreshResponse;
import com.certimetergroup.qrestaurant.manager.response.ManagerRegistrationResponse;
import com.certimetergroup.qrestaurant.manager.service.DeviceManagerService;
import com.certimetergroup.qrestaurant.manager.service.ManagerJwtService;
import com.certimetergroup.qrestaurant.manager.service.ManagerService;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
@RestController
public class ManagerController {

    @Value("${operating.system.type.web.device.manager}")
    private String operatingSystem;

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ManagerJwtService jwtService;
    @Autowired
    private DeviceManagerService deviceManagerService;

    /*
     * ENDPOINT USED TO  REGISTER THE MANAGER
     * @param registrationRequest contains the manager(id, name, surname, phone, password) and the uuid of the device
     * @return manager(id, name, surname, phone), jwt access token and jwt refresh token
     */
    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@Valid @RequestBody ManagerRegistrationRequest registrationRequest) {
        Manager manager = registrationRequest.getManager();
        managerService.registerManager(manager);
        String refreshToken = jwtService.generateRefreshToken(manager, registrationRequest.getUuid());
        String accessToken = jwtService.generateAccessToken(manager, registrationRequest.getUuid());
        DeviceManager deviceManager =
                new DeviceManager(0, null, null, refreshToken, registrationRequest.getUuid(), this.operatingSystem, manager.getIdManager());
        deviceManagerService.registerDevice(deviceManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ManagerRegistrationResponse(ResponseType.SUCCESS, manager, accessToken, refreshToken));
    }

    /*
     * ENDPOINT USED TO  LOGIN THE MANAGER
     * @param registrationRequest contains uuid, phone and  password
     * @return manager(id, name, surname, phone), jwt access token and jwt refresh token
     */
    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody ManagerLoginRequest managerLoginRequest) {
        Manager manager = managerService.loginManager(managerLoginRequest.getPhone(), managerLoginRequest.getPassword());
        String refreshToken = jwtService.generateRefreshToken(manager, managerLoginRequest.getUuid());
        String accessToken = jwtService.generateAccessToken(manager, managerLoginRequest.getUuid());
        DeviceManager deviceManager =
                new DeviceManager(0, null, null, refreshToken, managerLoginRequest.getUuid(), this.operatingSystem, manager.getIdManager());
        deviceManagerService.registerDevice(deviceManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ManagerRegistrationResponse(ResponseType.SUCCESS, manager, accessToken, refreshToken));
    }

    /*
     * ENDPOINT USED TO  REFRESH ACCESS TOKEN OF THE MANAGER
     * @param accessToken is manager's JWT access token
     * @param refreshToken is manager's JWT refresh token
     * @return manager(id, name, surname, phone), new access token and new refresh token
     */
    @PostMapping("/login/refresh")
    public ResponseEntity<Response> refresh(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                            @RequestBody @Valid RefreshRequest refreshRequest) {

        jwtService.isAccessTokenExpired(accessToken);
        String uuidDeviceManager = jwtService.getFieldFromRefreshToken(refreshRequest.getRefreshToken(), "UUID", String.class);
        DeviceManager deviceManager = deviceManagerService.getDeviceManager(uuidDeviceManager);
        Manager manager = managerService.getManager(deviceManager.getIdManagerFK());
        jwtService.validateRefreshToken(refreshRequest.getRefreshToken(), deviceManager);

        String newAccessToken = jwtService.generateAccessToken(manager, uuidDeviceManager);
        String newRefreshToken = jwtService.generateRefreshToken(manager, uuidDeviceManager);

        deviceManager.setRefreshToken(newRefreshToken);
        deviceManagerService.updateDeviceManager(deviceManager);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ManagerLoginRefreshResponse(ResponseType.SUCCESS, newAccessToken, newRefreshToken, manager));
    }

}
