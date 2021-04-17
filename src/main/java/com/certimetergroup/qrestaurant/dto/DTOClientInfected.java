package com.certimetergroup.qrestaurant.dto;

import com.certimetergroup.qrestaurant.model.Client;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class DTOClientInfected extends DTOClient {
    Boolean infected;
    Timestamp infectedAt;

    public DTOClientInfected(@NotNull Integer idClient, @NotEmpty String name, @NotEmpty String surname, @NotEmpty String phone, Boolean infected, Timestamp infectedAt) {
        super(idClient, name, surname, phone);
        this.infected = infected;
        this.infectedAt = infectedAt;
    }

    public DTOClientInfected() {
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

    public Client toClient() {
        Client client = new Client();
        BeanUtils.copyProperties(this, client);
        return client;
    }
}
