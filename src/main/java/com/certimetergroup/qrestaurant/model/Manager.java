package com.certimetergroup.qrestaurant.model;

import com.certimetergroup.qrestaurant.dto.DTOManagerRequest;
import com.certimetergroup.qrestaurant.dto.DTOManagerResponse;
import org.springframework.beans.BeanUtils;

public class Manager {
    private Integer idManager;
    private String name;
    private String surname;
    private String phone;
    private String password;

    public Manager(Integer idManager, String name, String surname, String phone, String password) {
        this.idManager = idManager;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
    }

    public Manager() {
    }

    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DTOManagerRequest toRequestDTO() {
        DTOManagerRequest manager = new DTOManagerRequest();
        BeanUtils.copyProperties(this, manager);
        return manager;
    }

    public DTOManagerResponse toResponseDTO() {
        DTOManagerResponse manager = new DTOManagerResponse();
        BeanUtils.copyProperties(this, manager);
        return manager;
    }
}
