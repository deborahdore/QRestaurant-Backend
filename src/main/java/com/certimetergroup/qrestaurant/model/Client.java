package com.certimetergroup.qrestaurant.model;

import com.certimetergroup.qrestaurant.dto.DTOClient;
import com.certimetergroup.qrestaurant.dto.DTOClientInfected;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

public class Client {
    private Integer idClient;
    private String name;
    private String surname;
    private String phone;
    private Boolean infected;
    private Timestamp infectedAt;

    public Client() {
    }

    public Client(Integer idClient, String name, String surname, String phone, Boolean infected, Timestamp infectedAt) {
        this.idClient = idClient;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.infected = infected;
        this.infectedAt = infectedAt;
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

    public Boolean getInfected() {
        return infected;
    }

    public void setInfected(Boolean infected) {
        this.infected = infected;
    }

    public Timestamp getInfectedAt() {
        return infectedAt;
    }

    public void setInfectedAt(Timestamp infectedAt) {
        this.infectedAt = infectedAt;
    }

    public DTOClient toDTO() {
        DTOClient client = new DTOClient();
        BeanUtils.copyProperties(this, client);
        return client;
    }

    public DTOClientInfected toDTOInfected() {
        DTOClientInfected client = new DTOClientInfected();
        BeanUtils.copyProperties(this, client);
        return client;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", infected=" + infected +
                ", infectedAt=" + infectedAt +
                '}';
    }
}
