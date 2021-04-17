package com.certimetergroup.qrestaurant.dto;

import com.certimetergroup.qrestaurant.model.Society;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DTOSociety {
    @NotNull
    Integer idSociety;
    @NotEmpty
    String street;
    @NotEmpty
    String city;
    @NotEmpty
    String country;
    @NotEmpty
    String societyName;
    @NotEmpty
    String vatnum;

    public DTOSociety() {
    }

    public DTOSociety(@NotNull Integer idSociety, @NotEmpty String street, @NotEmpty String city, @NotEmpty String country, @NotEmpty String societyName, @NotEmpty String vatnum) {
        this.idSociety = idSociety;
        this.street = street;
        this.city = city;
        this.country = country;
        this.societyName = societyName;
        this.vatnum = vatnum;
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

    public Society toSociety() {
        Society society = new Society();
        BeanUtils.copyProperties(this, society);
        return society;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DTOSociety that = (DTOSociety) o;
        return Objects.equals(idSociety, that.idSociety) &&
                Objects.equals(street, that.street) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country) &&
                Objects.equals(societyName, that.societyName) &&
                Objects.equals(vatnum, that.vatnum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSociety, street, city, country, societyName, vatnum);
    }
}
