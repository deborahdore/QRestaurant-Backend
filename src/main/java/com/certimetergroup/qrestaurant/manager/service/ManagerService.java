package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.repository.ManagerRepository;
import com.certimetergroup.qrestaurant.service.ManagerBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ManagerService extends ManagerBaseService {
    @Autowired
    private ManagerRepository managerRepository;

    public void registerManager(Manager manager) {
        Manager findManager = null;
        try {
            /* manager must be null to be registered ->
             * if correct, null manager will cause an exception */
            findManager = this.getManagerByPhone(manager.getPhone());
        } catch (FailureException e) {
            insertManager(manager);
        }
        if (findManager != null)
            throw new FailureException(ResponseType.INVALID_REGISTRATION, HttpStatus.UNAUTHORIZED);
    }

    public Manager loginManager(String phone, String password) {
        Manager manager = managerRepository.getManagerByPhoneAndPassword(phone, password);
        if (manager == null)
            throw new FailureException(ResponseType.INVALID_LOGIN, HttpStatus.UNAUTHORIZED);
        return manager;
    }
}
