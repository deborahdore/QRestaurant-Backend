package com.certimetergroup.qrestaurant.client.response;

import com.certimetergroup.qrestaurant.dto.DTOClientInfected;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.response.Response;

public class GetInfectedResponse extends Response {
    private DTOClientInfected client;

    public GetInfectedResponse(ResponseType response, Client client) {
        super(response);
        this.client = client.toDTOInfected();
    }

    public DTOClientInfected getClient() {
        return client;
    }

    public void setClient(DTOClientInfected client) {
        this.client = client;
    }
}
