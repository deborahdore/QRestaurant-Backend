package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.repository.DeviceManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceManagerBaseService {
    @Autowired
    private DeviceManagerRepository deviceManagerRepository;

    /*** GET ***/
    public List<DeviceManager> getDeviceManager(Integer idDeviceManager) {
        return deviceManagerRepository.getDeviceManager(idDeviceManager);
    }

    public DeviceManager getDeviceManager(String UUID) {
        DeviceManager deviceManager = deviceManagerRepository.getDeviceManagerByUUID(UUID);
        if (deviceManager == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return deviceManager;
    }

    /*** POST ***/
    public void insertDeviceManager(DeviceManager deviceManager) {
        int rowsInserted = deviceManagerRepository.postDeviceManager(deviceManager);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /*** PUT ***/
    public void updateDeviceManager(DeviceManager deviceManager) {
        int rowsUpdated = deviceManagerRepository.putDeviceManager(deviceManager);
        if (rowsUpdated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }

    /*** DELETE ***/
    public void deleteDeviceManager(DeviceManager deviceManager) {
        int rowsDeleted = deviceManagerRepository.deleteDeviceManager(deviceManager);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }

    public void deleteDeviceManager(Integer idDeviceManager) {
        int rowsDeleted = deviceManagerRepository.deleteDeviceManagerByID(idDeviceManager);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }

}
