package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Manager2Society;
import com.certimetergroup.qrestaurant.repository.Manager2SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Manager2SocietyBaseService {
    @Autowired
    private Manager2SocietyRepository manager2SocietyRepository;

    /**
     * GET
     */

    public Manager2Society getManager2Society(Integer idManager2Society) {
        Manager2Society manager2Society = manager2SocietyRepository.getManager2Society(idManager2Society);
        if (manager2Society == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return manager2Society;
    }

    public List<Integer> getIdManagersOfSociety(Integer idSociety) {
        return manager2SocietyRepository.getManagersOfSociety(idSociety);
    }

    /**
     * POST
     */
    public void insertManager2Society(Manager2Society manager2Society) {
        int rowsCreated = manager2SocietyRepository.postManager2Society(manager2Society);
        if (rowsCreated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * PUT
     */
    public void updateManager2Society(Manager2Society manager2Society) {
        int rowsUpdated = manager2SocietyRepository.putManager2Society(manager2Society);
        if (rowsUpdated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * DELETE
     */
    public void deleteManager2Society(Manager2Society manager2Society) {
        int rowsDeleted = manager2SocietyRepository.deleteManager2Society(manager2Society);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void deleteManager2Society(Integer idManager2Society) {
        int rowsDeleted = manager2SocietyRepository.deleteManager2SocietyByID(idManager2Society);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

}
