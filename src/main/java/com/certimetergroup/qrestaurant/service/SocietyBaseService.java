package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.repository.SocietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SocietyBaseService {
    @Autowired
    private SocietyRepository societyRepository;

    /**
     * GET
     */
    public Society getSociety(Integer idSociety) {
        Society society = societyRepository.getSociety(idSociety);
        if (society == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return society;
    }

    /**
     * POST
     */
    public void insertSociety(Society society) {
        int rowsInserted = societyRepository.insertSociety(society);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * PUT
     */
    public void updateSociety(Society society) {
        int rowsUpdated = societyRepository.updateSociety(society);
        if (rowsUpdated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);

    }

    /**
     * DELETE
     */
    public void deleteSociety(Integer idSociety) {
        int rowsDeleted = societyRepository.deleteSocietyByID(idSociety);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void deleteSociety(Society society) {
        int rowsDeleted = societyRepository.deleteSociety(society);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }
}
