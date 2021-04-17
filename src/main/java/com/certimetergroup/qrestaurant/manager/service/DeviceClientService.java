package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.repository.DeviceClientRepository;
import com.certimetergroup.qrestaurant.service.DeviceClientBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceClientService extends DeviceClientBaseService {

    @Autowired
    private DeviceClientRepository deviceClientRepository;

    public DeviceClient getDeviceClient(String qrcode) {
        DeviceClient deviceClient = deviceClientRepository.getDeviceClientByQRCode(qrcode);
        if (deviceClient == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return deviceClient;
    }

    public List<DeviceClient> getClientDevices(Integer idClient) {
        return deviceClientRepository.getClientDevices(idClient);
    }
}
