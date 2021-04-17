package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.DeviceManager;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DeviceManagerMapper {

    @Select("SELECT * FROM DeviceManager WHERE idDeviceManager=#{idDeviceManager}")
    List<DeviceManager> getDeviceManager(@Param("idDeviceManager") Integer idDeviceManager);

    @Insert("INSERT INTO DeviceManager(firebaseRegistrationToken, createAt, refreshToken, uuid,operatingSystem, idManagerFK) " +
            "VALUES (#{deviceManager.firebaseRegistrationToken}, #{deviceManager.createAt}, #{deviceManager.refreshToken}, " +
            "#{deviceManager.uuid}, #{deviceManager.operatingSystem}, #{deviceManager.idManagerFK})")
    @Options(useGeneratedKeys = true, keyProperty = "idDeviceManager", keyColumn = "idDeviceManager")
    int postDeviceManager(@Param("deviceManager") DeviceManager deviceManager);

    @Update("UPDATE DeviceManager SET firebaseRegistrationToken=#{deviceManager.firebaseRegistrationToken}, " +
            "createAt=#{deviceManager.createAt}, refreshToken=#{deviceManager.refreshToken}, uuid=#{deviceManager.uuid}, operatingSystem=#{deviceManager.operatingSystem}, " +
            "idManagerFK=#{deviceManager.idManagerFK} WHERE idDeviceManager=#{deviceManager.idDeviceManager}")
    int putDeviceManager(@Param("deviceManager") DeviceManager deviceManager);

    @Delete("DELETE FROM DeviceManager WHERE idDeviceManager=#{deviceManager.idDeviceManager}")
    int deleteDeviceManager(@Param("deviceManager") DeviceManager deviceManager);

    @Delete("DELETE FROM DeviceManager WHERE idDeviceManager=#{idDeviceManager}")
    int deleteDeviceManagerByID(@Param("idDeviceManager") Integer idDeviceManager);

    @Select("SELECT * FROM DeviceManager WHERE uuid=#{uuid}")
    DeviceManager getDeviceManagerByUUID(@Param("uuid") String uuid);

    @Delete("DELETE FROM DeviceManager WHERE uuid=#{uuid}")
    int deleteDeviceManagerByUUID(@Param("uuid") String uuid);

    @Update("UPDATE DeviceManager SET firebaseRegistrationToken=#{deviceManager.firebaseRegistrationToken}, " +
            "createAt=#{deviceManager.createAt}, refreshToken=#{deviceManager.refreshToken}, operatingSystem=#{deviceManager.operatingSystem}, " +
            "idManagerFK=#{deviceManager.idManagerFK} WHERE uuid=#{deviceManager.uuid}")
    int putDeviceManagerByUUID(@Param("deviceManager") DeviceManager deviceManager);

    @Select("SELECT * FROM DeviceManager WHERE idManagerFK=#{idManager}")
    List<DeviceManager> getDeviceManagerByIdManager(@Param("idManager") Integer idManager);
}
