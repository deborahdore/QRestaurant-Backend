package com.certimetergroup.qrestaurant.model;

import com.certimetergroup.qrestaurant.dto.DTOSociety;
import org.springframework.beans.BeanUtils;

public class Society {
    private Integer idSociety;
    private String street;
    private String city;
    private String country;
    private String societyName;
    private String vatnum;

    public Society(Integer idSociety, String street, String city, String country, String societyName, String vatnum) {
        this.idSociety = idSociety;
        this.street = street;
        this.city = city;
        this.country = country;
        this.societyName = societyName;
        this.vatnum = vatnum;
    }

    public Society() {
    }

    public Integer getIdSociety() {
        return idSociety;
    }

    public void setIdSociety(Integer idSociety) {
        this.idSociety = idSociety;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSocietyName() {
        return societyName;
    }

    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }

    public String getVatnum() {
        return vatnum;
    }

    public void setVatnum(String vatnum) {
        this.vatnum = vatnum;
    }

    public DTOSociety toDTO() {
        DTOSociety society = new DTOSociety();
        BeanUtils.copyProperties(this, society);
        return society;
    }

    @Override
    public String toString() {
        return "RISTORANTE = Nome: " + societyName + "; " +
                "Indirizzo: " + street + ", " + city + ", " + country + "; " +
                "P.IVA: " + vatnum + ";";
    }
}
