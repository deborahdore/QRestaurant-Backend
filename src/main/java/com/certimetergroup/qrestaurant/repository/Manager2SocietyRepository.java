package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.Manager2SocietyMapper;
import com.certimetergroup.qrestaurant.model.Manager2Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Manager2SocietyRepository {
    @Autowired
    private Manager2SocietyMapper manager2SocietyMapper;

    public Manager2Society getManager2Society(Integer idManager2Society) {
        return manager2SocietyMapper.getManager2Society(idManager2Society);
    }

    public Manager2Society getManager2SocietyByIDManagerAndIDSociety(Integer idManager, Integer idSociety) {
        return manager2SocietyMapper.getManager2SocietyByIDManagerAndIDSociety(idManager, idSociety);
    }

    public int postManager2Society(Manager2Society manager2Society) {
        return manager2SocietyMapper.postManager2Society(manager2Society);
    }

    public int putManager2Society(Manager2Society manager2Society) {
        return manager2SocietyMapper.putManager2Society(manager2Society);
    }

    public int deleteManager2Society(Manager2Society manager2Society) {
        return manager2SocietyMapper.deleteManager2Society(manager2Society);
    }

    public int deleteManager2SocietyByID(Integer idManager2Society) {
        return manager2SocietyMapper.deleteManager2SocietyByID(idManager2Society);
    }

    public List<Integer> getManagersOfSociety(Integer idSociety) {
        return manager2SocietyMapper.getManagersOfSociety(idSociety);
    }

    public List<Integer> getSocietiesOfManager(Integer idManager) {
        return manager2SocietyMapper.getSocietiesOfManager(idManager);
    }
}
