package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.DeviceManagerMapper;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeviceManagerRepository {
    @Autowired
    private DeviceManagerMapper deviceManagerMapper;

    public List<DeviceManager> getDeviceManager(Integer idDeviceManager) {
        return deviceManagerMapper.getDeviceManager(idDeviceManager);
    }

    public int postDeviceManager(DeviceManager deviceManager) {
        return deviceManagerMapper.postDeviceManager(deviceManager);
    }

    public int putDeviceManager(DeviceManager deviceManager) {
        return deviceManagerMapper.putDeviceManager(deviceManager);
    }

    public int deleteDeviceManager(DeviceManager deviceManager) {
        return deviceManagerMapper.deleteDeviceManager(deviceManager);
    }

    public int deleteDeviceManagerByID(Integer idDeviceManager) {
        return deviceManagerMapper.deleteDeviceManagerByID(idDeviceManager);
    }

    public DeviceManager getDeviceManagerByUUID(String uuid) {
        return deviceManagerMapper.getDeviceManagerByUUID(uuid);
    }

    public int deleteDeviceManagerByUUID(String UUID) {
        return deviceManagerMapper.deleteDeviceManagerByUUID(UUID);
    }

    public int putDeviceManagerByUUID(DeviceManager deviceManager) {
        return deviceManagerMapper.putDeviceManagerByUUID(deviceManager);
    }

    public List<DeviceManager> getDeviceManagerByIdManager(Integer idManager) {
        return deviceManagerMapper.getDeviceManagerByIdManager(idManager);
    }
}
