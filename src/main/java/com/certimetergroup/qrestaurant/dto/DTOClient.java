package com.certimetergroup.qrestaurant.dto;

import com.certimetergroup.qrestaurant.model.Client;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DTOClient {
    @NotNull
    Integer idClient;
    @NotEmpty
    String name;
    @NotEmpty
    String surname;
    @NotEmpty
    String phone;

    public DTOClient() {
    }

    public DTOClient(@NotNull Integer idClient, @NotEmpty String name, @NotEmpty String surname, @NotEmpty String phone) {
        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
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

    public Client toClient() {
        Client client = new Client();
        client.setInfected(false);
        BeanUtils.copyProperties(this, client);
        return client;
    }
}
