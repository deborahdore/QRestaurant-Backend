package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.Manager;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ManagerMapper {
    @Select("SELECT * FROM Manager WHERE idManager=#{idManager}")
    Manager getManager(@Param("idManager") Integer idManager);

    @Insert("INSERT INTO Manager(name, surname, phone, password) " +
            "VALUES (#{manager.name}, #{manager.surname}, #{manager.phone}, #{manager.password})")
    @Options(useGeneratedKeys = true, keyProperty = "idManager", keyColumn = "idManager")
    int postManager(@Param("manager") Manager manager);

    @Update("UPDATE Manager SET name=#{manager.name}, surname=#{manager.surname}, phone=#{manager.phone}, password=#{manager.password} " +
            "WHERE idManager=#{manager.idManager}")
    int putManager(@Param("manager") Manager manager);

    @Delete("DELETE FROM Manager WHERE idManager=#{manager.idManager}")
    int deleteManager(@Param("manager") Manager manager);

    @Delete("DELETE FROM Manager WHERE idManager=#{idManager}")
    int deleteManagerByID(@Param("idManager") Integer idManager);

    @Select("SELECT * FROM Manager WHERE phone=#{phone}")
    Manager getManagerByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM Manager WHERE phone=#{phone} and password=#{password}")
    Manager getManagerByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

    @Select("SELECT * FROM Manager")
    List<Manager> getAllManagers();
}
