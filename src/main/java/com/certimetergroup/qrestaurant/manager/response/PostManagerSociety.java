package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOSociety;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.response.Response;

public class PostManagerSociety extends Response {
    private final DTOSociety society;

    public PostManagerSociety(ResponseType response, Society society) {
        super(response);
        this.society = society.toDTO();
    }

    public DTOSociety getSociety() {
        return society;
    }
}
