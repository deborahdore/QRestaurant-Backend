package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.repository.DeviceClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceClientBaseService {
    @Autowired
    private DeviceClientRepository deviceClientRepository;

    /**
     * GET
     **/
    public DeviceClient getDeviceClient(Integer idDeviceClient) {
        DeviceClient deviceClient = deviceClientRepository.getDeviceByID(idDeviceClient);
        if (deviceClient == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return deviceClient;
    }

    public DeviceClient getDeviceClientByRegistrationToken(String registrationToken) {
        return deviceClientRepository.getDeviceClientByRegistrationToken(registrationToken);
    }

    public List<DeviceClient> getAllDevices() {
        return deviceClientRepository.getAllDeviceClient();
    }

    /**
     * POST
     **/
    public void insertNewDevice(DeviceClient deviceClient) {
        int generatedID = deviceClientRepository.insertNewDevice(deviceClient);
        if (generatedID < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * PUT
     **/
    public void updateDevice(DeviceClient deviceClient) {
        int rowsUpdated = deviceClientRepository.updateDevice(deviceClient);
        if (rowsUpdated < 1) {
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * DELETE
     **/
    public void deleteDevice(Integer idDeviceClient) {
        int rowsDeleted = deviceClientRepository.deleteDeviceByID(idDeviceClient);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void deleteDevice(DeviceClient deviceClient) {
        int rowsDeleted = deviceClientRepository.deleteDeviceByID(deviceClient.getIdDeviceClient());
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

}
