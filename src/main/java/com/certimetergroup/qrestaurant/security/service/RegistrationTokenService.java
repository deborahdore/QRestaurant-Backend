package com.certimetergroup.qrestaurant.security.service;

import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.security.exception.AuthenticationErrException;
import com.certimetergroup.qrestaurant.service.DeviceClientBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegistrationTokenService {
    @Autowired
    private DeviceClientBaseService deviceClientBaseService;

    public void validateRegistrationToken(String registrationToken) {
        if (deviceClientBaseService.getDeviceClientByRegistrationToken(registrationToken) == null)
            throw new AuthenticationErrException(AuthenticationErrType.INVALID_REGISTRATION_TOKEN, HttpStatus.UNAUTHORIZED);
    }
}
