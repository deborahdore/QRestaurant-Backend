package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTOSociety;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Society;
import com.certimetergroup.qrestaurant.response.Response;

import java.util.List;
import java.util.stream.Collectors;

public class GetSocietiesResponse extends Response {
    List<DTOSociety> societies;
    Integer numSocieties;

    public GetSocietiesResponse(ResponseType response, List<Society> societies) {
        super(response);
        this.societies = societies.stream().map(Society::toDTO).collect(Collectors.toList());
        this.numSocieties = societies.size();
    }


    public List<DTOSociety> getSocieties() {
        return societies;
    }

    public Integer getNumSocieties() {
        return numSocieties;
    }
}
