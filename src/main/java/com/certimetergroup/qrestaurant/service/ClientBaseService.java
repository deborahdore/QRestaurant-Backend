package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientBaseService {
    @Autowired
    private ClientRepository clientRepository;

    /**
     * GET
     **/
    public Client getClient(Integer idClient) {
        Client client = clientRepository.getClientByID(idClient);
        if (client == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return client;
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    /**
     * POST
     **/
    public void insertNewClient(Client client) {
        int rowsInserted = clientRepository.insertNewClient(client);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * PUT
     **/
    public void updateClient(Client client) {
        int rowsUpdated = clientRepository.updateClient(client);
        if (rowsUpdated < 1) {
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * DELETE
     **/
    public void deleteClient(Integer idClient) {
        int rowsDeleted = clientRepository.deleteClientByID(idClient);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void deleteClient(Client client) {
        int rowsDeleted = clientRepository.deleteClientByID(client.getIdClient());
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

}
