package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOClientAttendance;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.response.Response;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetClientsAttendanceInDateResponse extends Response {
    private final List<DTOClientAttendance> clients = new ArrayList<>();
    private final Integer numClients;

    public GetClientsAttendanceInDateResponse(ResponseType response, Map<Client, Timestamp> clients) {
        super(response);
        clients.forEach(((client, timestamp) -> {
            DTOClientAttendance clientAttendance = new DTOClientAttendance(client, timestamp);
            this.clients.add(clientAttendance);
        }));
        this.numClients = clients.size();
    }

    public List<DTOClientAttendance> getClients() {
        return clients;
    }

    public Integer getNumClients() {
        return numClients;
    }
}
