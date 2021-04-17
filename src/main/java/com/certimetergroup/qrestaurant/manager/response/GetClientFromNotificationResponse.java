package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOClient;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class GetClientFromNotificationResponse extends Response {
    private final List<DTOClient> clients;
    private final Integer numClients;

    public GetClientFromNotificationResponse(ResponseType response, List<Client> clients) {
        super(response);
        this.clients = clients.stream().map(Client::toDTO).collect(Collectors.toList());
        this.numClients = clients.size();
    }

    public List<DTOClient> getClients() {
        return clients;
    }

    public Integer getNumClients() {
        return numClients;
    }
}
