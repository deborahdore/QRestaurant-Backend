package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerBaseService {
    @Autowired
    private ManagerRepository managerRepository;

    /*** GET ***/
    public Manager getManager(Integer idManager) {
        Manager manager = managerRepository.getManager(idManager);
        if (manager == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return manager;
    }

    public List<Manager> getAllManagers() {
        return managerRepository.getAllManagers();
    }

    public Manager getManagerByPhone(String phone) {
        Manager manager = managerRepository.getManagerByPhone(phone);
        if (manager == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return manager;
    }


    /*** POST ***/
    public void insertManager(Manager manager) {
        int rowsInserted = managerRepository.postManager(manager);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /*** PUT ***/
    public void updateManager(Manager manager) {
        int rowsUpdated = managerRepository.putManager(manager);
        if (rowsUpdated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }

    /*** DELETE ***/
    public void deleteManager(Manager manager) {
        int rowsDeleted = managerRepository.deleteManager(manager);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }

    public void deleteManager(Integer idManager) {
        int rowsDeleted = managerRepository.deleteManagerByID(idManager);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }
}
