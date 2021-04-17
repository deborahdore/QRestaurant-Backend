package com.certimetergroup.qrestaurant.client.request;

import com.certimetergroup.qrestaurant.dto.DTOClient;
import com.certimetergroup.qrestaurant.model.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class ClientRegistrationRequest {
    @Valid
    private DTOClient client;
    @NotEmpty
    private String uuid;

    public ClientRegistrationRequest(@Valid DTOClient client, @NotEmpty String uuid) {
        this.client = client;
        this.uuid = uuid;
    }

    public ClientRegistrationRequest() {
    }

    public DTOClient getDTOClient() {
        return client;
    }

    public Client getClient() {
        return this.client.toClient();
    }

    public void setClient(DTOClient client) {
        this.client = client;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
