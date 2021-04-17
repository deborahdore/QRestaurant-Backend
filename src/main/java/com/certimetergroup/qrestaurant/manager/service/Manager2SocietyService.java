package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.repository.Manager2SocietyRepository;
import com.certimetergroup.qrestaurant.service.Manager2SocietyBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Manager2SocietyService extends Manager2SocietyBaseService {
    @Autowired
    private Manager2SocietyRepository manager2SocietyRepository;
    @Autowired
    private SocietyService societyService;

    public List<Society> getSocietiesOfManager(Integer idManager) {
        List<Integer> idSocieties = manager2SocietyRepository.getSocietiesOfManager(idManager);
        List<Society> societies = new ArrayList<>();
        for (Integer idSociety : idSocieties) {
            societies.add(societyService.getSociety(idSociety));
        }
        return societies;
    }

    public boolean isManagerSociety(Integer idManager, Integer idSociety) {
        return manager2SocietyRepository.getManager2SocietyByIDManagerAndIDSociety(idManager, idSociety) != null;
    }
}
