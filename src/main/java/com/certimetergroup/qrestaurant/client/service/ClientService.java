package com.certimetergroup.qrestaurant.client.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.repository.ClientRepository;
import com.certimetergroup.qrestaurant.service.ClientBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ClientService extends ClientBaseService {
    @Autowired
    private ClientRepository clientRepository;

    public int insertNewClientReturnGeneratedID(Client client) {
        Client searchClient = doesClientExists(client);
        if (searchClient != null)
            return searchClient.getIdClient();

        int rowsInserted = clientRepository.insertNewClient(client);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return client.getIdClient();
    }

    public Client doesClientExists(Client client) {
        Client tmpClient = clientRepository.getClientByPhone(client.getPhone());
        if (tmpClient != null &&
                ((!client.getName().equals(tmpClient.getName()))
                        || (!client.getSurname().equals(tmpClient.getSurname())))) {
            throw new FailureException(ResponseType.INVALID_LOGIN, HttpStatus.UNAUTHORIZED);
        }
        return tmpClient;
    }

    public void setClientInfected(Client client, Boolean value) {
        client.setInfected(value);
        if (value)
            client.setInfectedAt(new Timestamp(System.currentTimeMillis()));
        else
            client.setInfectedAt(null);
        this.updateClient(client);
    }
}
