package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.DeviceClientMapper;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceClientRepository {
    @Autowired
    private DeviceClientMapper deviceClientMapper;


    public int insertNewDevice(DeviceClient device) {
        return deviceClientMapper.postDevice(device);
    }

    public List<DeviceClient> getAllDeviceClient() {
        return deviceClientMapper.getAllDevices();
    }

    public String getRegistrationToken(DeviceClient deviceClient) {
        return deviceClientMapper.getRegistrationToken(deviceClient);
    }

    public String getRegistrationToken(Integer idDeviceClient) {
        return deviceClientMapper.getRegistrationTokenByIDDeviceClient(idDeviceClient);
    }

    public DeviceClient getDeviceByID(Integer idDeviceClient) {
        return deviceClientMapper.getDeviceByID(idDeviceClient);
    }

    public int updateDevice(DeviceClient device) {
        return deviceClientMapper.putDevice(device);
    }

    public int deleteDeviceByID(Integer idDeviceClient) {
        return deviceClientMapper.deleteDeviceByID(idDeviceClient);
    }

    public DeviceClient getDeviceClientByRegistrationToken(String registrationToken) {
        return deviceClientMapper.getDeviceByRegistrationToken(registrationToken);
    }

    public DeviceClient getDeviceClientByQRCode(String qrcode) {
        return deviceClientMapper.getDeviceByQRCode(qrcode);
    }

    public int updateDeviceByUUID(DeviceClient deviceClient) {
        return deviceClientMapper.updateDeviceByUUID(deviceClient);
    }

    public List<DeviceClient> getClientDevices(Integer idClient) {
        return deviceClientMapper.getClientDevices(idClient);
    }
}
