package com.certimetergroup.qrestaurant.dto;

import com.certimetergroup.qrestaurant.model.Manager;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DTOManagerResponse {
    @NotNull
    Integer idManager;
    @NotEmpty
    String name;
    @NotEmpty
    String surname;
    @NotEmpty
    String phone;

    public DTOManagerResponse() {
    }

    public DTOManagerResponse(@NotNull Integer idManager, @NotEmpty String name, @NotEmpty String surname, @NotEmpty String phone) {
        this.idManager = idManager;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
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

    public Manager toManager() {
        Manager manager = new Manager();
        BeanUtils.copyProperties(this, manager);
        return manager;
    }
}
