package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.Society;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

@Mapper
public interface SocietyMapper {
    @Select("SELECT * FROM Society WHERE idSociety=#{idSociety}")
    Society getSociety(@Param("idSociety") Integer idSociety);

    @Insert("INSERT INTO Society(street, city, country, societyName, vatnum) " +
            "VALUES (#{society.street}, #{society.city}, #{society.country}, #{society.societyName}, #{society.vatnum})")
    @Options(useGeneratedKeys = true, keyProperty = "idSociety", keyColumn = "idSociety")
    int postSociety(@Param("society") Society society);


    @Update("UPDATE Society SET " +
            "street=#{society.street}, city=#{society.city}, country=#{society.country}, societyName=#{society.societyName}, " +
            "vatnum=#{society.vatnum} WHERE idSociety=#{society.idSociety}")
    int putSociety(@Param("society") Society society);

    @Delete("DELETE FROM Society WHERE idSociety=#{idSociety}")
    int deleteSocietyByID(@Param("idSociety") Integer idSociety);

    @Delete("DELETE FROM Society WHERE idSociety=#{society.idSociety}")
    int deleteSociety(@Param("society") Society society);
}
