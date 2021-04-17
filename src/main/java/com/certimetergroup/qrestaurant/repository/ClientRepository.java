package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.ClientMapper;
import com.certimetergroup.qrestaurant.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
    @Autowired
    private ClientMapper clientMapper;

    public int insertNewClient(Client client) {
        return clientMapper.postClient(client);
    }

    public List<Client> getAllClients() {
        return clientMapper.getAllClients();
    }

    public Client getClientByID(Integer idClient) {
        return clientMapper.getClientByID(idClient);
    }

    public int updateClient(Client client) {
        return clientMapper.putClient(client);
    }

    public int deleteClientByID(Integer idClient) {
        return clientMapper.deleteClientByID(idClient);
    }

    public Client getClientByPhone(String phone) {
        return clientMapper.getClientByPhone(phone);
    }

    public int patchInfectedClientByID(Integer idClient, Boolean value) {
        return clientMapper.patchInfectedClientByID(idClient, value);
    }
}
