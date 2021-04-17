package com.certimetergroup.qrestaurant.dto;

import com.certimetergroup.qrestaurant.model.Client;

import java.sql.Timestamp;

public class DTOClientAttendance {
    DTOClient client;
    Timestamp arrivalTime;

    public DTOClientAttendance(Client client, Timestamp arrivalTime) {
        this.client = client.toDTO();
        this.arrivalTime = arrivalTime;
    }

    public DTOClient getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client.toDTO();
    }

    public void setClient(DTOClient client) {
        this.client = client;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
