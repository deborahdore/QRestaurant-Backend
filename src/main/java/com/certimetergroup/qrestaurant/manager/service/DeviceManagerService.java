package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.repository.DeviceManagerRepository;
import com.certimetergroup.qrestaurant.service.DeviceManagerBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeviceManagerService extends DeviceManagerBaseService {
    @Autowired
    private DeviceManagerRepository deviceManagerRepository;

    public void deleteDeviceManager(String uuid) {
        int rowsDeleted = deviceManagerRepository.deleteDeviceManagerByUUID(uuid);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void registerDevice(DeviceManager deviceManager) {
        int rowsUpdated = deviceManagerRepository.putDeviceManagerByUUID(deviceManager);
        if (rowsUpdated < 1) {
            int rowsInserted = deviceManagerRepository.postDeviceManager(deviceManager);
            if (rowsInserted < 1)
                throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        }
    }

}
