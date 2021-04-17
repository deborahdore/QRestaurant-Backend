package com.certimetergroup.qrestaurant.client.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.repository.DeviceClientRepository;
import com.certimetergroup.qrestaurant.service.DeviceClientBaseService;
import com.certimetergroup.qrestaurant.utility.QRCodeUtility;
import com.certimetergroup.qrestaurant.utility.RegistrationTokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class DeviceClientService extends DeviceClientBaseService {
    @Autowired
    private DeviceClientRepository deviceClientRepository;

    public DeviceClient insertNewDeviceReturnGeneratedDevice(String UUID, Integer idClient) {
        String registrationToken = RegistrationTokenUtility.generateRegistrationToken();
        String QRCode = QRCodeUtility.generateQRCode(UUID);
        DeviceClient deviceClient =
                new DeviceClient(0,
                        UUID,
                        registrationToken,
                        QRCode,
                        idClient);
        int rowsUpdated = this.updateDeviceByUUIDReturnRowsUpdated(deviceClient);
        if (rowsUpdated > 0)
            return deviceClient;

        int rowsInserted = deviceClientRepository.insertNewDevice(deviceClient);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.INVALID_REGISTRATION, HttpStatus.UNAUTHORIZED);
        return deviceClient;
    }

    private int updateDeviceByUUIDReturnRowsUpdated(DeviceClient deviceClient) {
        return deviceClientRepository.updateDeviceByUUID(deviceClient);
    }


    public String getRegistrationToken(Integer idDeviceClient) {
        return deviceClientRepository.getRegistrationToken(idDeviceClient);

    }
}
