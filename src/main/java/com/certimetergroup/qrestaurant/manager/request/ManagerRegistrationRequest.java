package com.certimetergroup.qrestaurant.manager.request;

import com.certimetergroup.qrestaurant.dto.DTOManagerRequest;
import com.certimetergroup.qrestaurant.model.Manager;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class ManagerRegistrationRequest {
    @Valid
    private DTOManagerRequest manager;
    @NotEmpty
    private String uuid;

    public ManagerRegistrationRequest(@Valid DTOManagerRequest manager, @NotEmpty String uuid) {
        this.manager = manager;
        this.uuid = uuid;
    }

    public ManagerRegistrationRequest() {
    }

    public Manager getManager() {
        return manager.toManager();
    }

    public void setManager(DTOManagerRequest manager) {
        this.manager = manager;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
