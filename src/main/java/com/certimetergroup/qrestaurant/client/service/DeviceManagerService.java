package com.certimetergroup.qrestaurant.client.service;

import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.repository.DeviceManagerRepository;
import com.certimetergroup.qrestaurant.service.DeviceManagerBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceManagerService extends DeviceManagerBaseService {
    @Autowired
    private DeviceManagerRepository deviceManagerRepository;

    public List<DeviceManager> getDeviceManagerByIdManager(Integer idManager) {
        return deviceManagerRepository.getDeviceManagerByIdManager(idManager);
    }
}
