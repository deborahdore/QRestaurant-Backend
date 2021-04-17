package com.certimetergroup.qrestaurant.client.controller;

import com.certimetergroup.qrestaurant.client.request.ClientRegistrationRequest;
import com.certimetergroup.qrestaurant.client.response.GetInfectedResponse;
import com.certimetergroup.qrestaurant.client.response.RegistrationResponse;
import com.certimetergroup.qrestaurant.client.service.ClientService;
import com.certimetergroup.qrestaurant.client.service.DeviceClientService;
import com.certimetergroup.qrestaurant.client.service.NotificationService;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Client;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.response.Response;
import com.certimetergroup.qrestaurant.utility.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Validated
@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private DeviceClientService deviceClientService;
    @Autowired
    private NotificationService notificationService;

    /*
     * ENDPOINT USED TO REGISTER THE CLIENT
     * @param clientRegistrationRequest contains the Client(id, name, surname, phone) and uuid
     * @return registration token in case of success
     */
    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@Valid @RequestBody ClientRegistrationRequest clientRegistrationRequest) {
        int clientID = clientService.insertNewClientReturnGeneratedID(clientRegistrationRequest.getClient());
        DeviceClient deviceClient = deviceClientService.insertNewDeviceReturnGeneratedDevice(clientRegistrationRequest.getUuid(), clientID);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new RegistrationResponse(ResponseType.SUCCESS,
                        deviceClient.getRegistrationToken()));
    }

    /*
     * ENDPOINT USED TO GET CLIENT'S INFECTION
     * @param registrationToken is client's registration token
     * @return client
     */
    @GetMapping("/infected")
    public ResponseEntity<Response> getInfected(@RequestHeader("Authorization-Client-Token") @NotEmpty String registrationToken) {
        DeviceClient deviceClient = deviceClientService.getDeviceClientByRegistrationToken(registrationToken);
        Client client = clientService.getClient(deviceClient.getIdClientFK());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetInfectedResponse(ResponseType.SUCCESS, client));
    }

    /*
     * ENDPOINT USED TO SET CLIENT'S INFECTION TO TRUE OR FALSE
     * @param registrationToken is client's registration token
     * @param value represent if client is infected or not
     * @return success if everything went fine
     */
    @PatchMapping("/infected/{value}")
    public ResponseEntity<Response> setInfected(@RequestHeader("Authorization-Client-Token") @NotEmpty String registrationToken,
                                                @PathVariable @NotNull Boolean value) {
        DeviceClient deviceClient = deviceClientService.getDeviceClientByRegistrationToken(registrationToken);
        Integer idClient = deviceClient.getIdClientFK();
        Client client = clientService.getClient(idClient);
        Boolean alreadyInfected = client.getInfected();
        clientService.setClientInfected(client, value);
        /* if client  hadn't already reported the infection
           sends notifications to societies where the infected client
           has been in the past 48 hours */
        if (value && !alreadyInfected) {
            notificationService.sendNotificationsClientInfected(idClient, DateUtility.selectPassedDate(48));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ResponseType.SUCCESS));
    }
}
