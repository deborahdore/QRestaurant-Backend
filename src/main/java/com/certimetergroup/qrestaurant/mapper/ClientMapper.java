package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.Client;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    @Insert("INSERT INTO Client(name, surname, phone, infected, infectedAt) " +
            "VALUES (#{client.name},#{client.surname}, #{client.phone}, #{client.infected}, #{client.infectedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "idClient", keyColumn = "idClient")
    int postClient(@Param("client") Client client);

    @Select("SELECT * FROM Client")
    List<Client> getAllClients();

    @Select("SELECT * FROM Client WHERE idClient = #{idClient}")
    Client getClientByID(@Param("idClient") Integer idClient);

    @Update("UPDATE Client SET name=#{client.name}, surname=#{client.surname}, " +
            "phone=#{client.phone}, infected=#{client.infected}, infectedAt=#{client.infectedAt} " +
            "WHERE idClient=#{client.idClient}")
    int putClient(@Param("client") Client client);

    @Delete("DELETE FROM Client WHERE idClient=#{idClient}")
    int deleteClientByID(@Param("idClient") Integer idClient);

    @Select("SELECT * FROM Client WHERE phone=#{phone}")
    Client getClientByPhone(@Param("phone") String phone);

    @Update("UPDATE Client SET infected=#{value} WHERE idClient = #{idClient}")
    int patchInfectedClientByID(@Param("idClient") Integer idClient, @Param("value") Boolean value);
}
