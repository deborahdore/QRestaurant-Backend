package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.SocietyMapper;
import com.certimetergroup.qrestaurant.model.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SocietyRepository {
    @Autowired
    private SocietyMapper societyMapper;

    public Society getSociety(Integer idSociety) {
        return societyMapper.getSociety(idSociety);
    }

    public int insertSociety(Society society) {
        return societyMapper.postSociety(society);
    }

    public int updateSociety(Society society) {
        return societyMapper.putSociety(society);
    }

    public int deleteSocietyByID(Integer idSociety) {
        return societyMapper.deleteSocietyByID(idSociety);
    }

    public int deleteSociety(Society society) {
        return societyMapper.deleteSociety(society);
    }
}
