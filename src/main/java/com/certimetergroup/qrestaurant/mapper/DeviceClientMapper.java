package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.DeviceClient;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DeviceClientMapper {
    @Insert("INSERT INTO DeviceClient(uuid, registrationToken, QRCode, firebaseRegistrationToken, createAt, idClientFK) " +
            "VALUES (#{device.uuid}, #{device.registrationToken}, #{device.QRCode}, #{device.firebaseRegistrationToken},  #{device.createAt},  #{device.idClientFK})")
    @Options(useGeneratedKeys = true, keyProperty = "idDeviceClient", keyColumn = "idDeviceClient")
    int postDevice(@Param("device") DeviceClient device);

    @Select("SELECT * FROM DeviceClient")
    List<DeviceClient> getAllDevices();

    @Select("SELECT registrationToken FROM DeviceClient WHERE idDeviceClient=#{deviceClient.idDeviceClient}")
    String getRegistrationToken(@Param("deviceClient") DeviceClient deviceClient);

    @Select("SELECT registrationToken FROM DeviceClient WHERE idDeviceClient=#{idDeviceClient}")
    String getRegistrationTokenByIDDeviceClient(@Param("idDeviceClient") Integer idDeviceClient);

    @Select("SELECT * FROM DeviceClient WHERE idDeviceClient = #{idDeviceClient}")
    DeviceClient getDeviceByID(@Param("idDeviceClient") Integer idDeviceClient);

    @Update("UPDATE DeviceClient SET " +
            "uuid=#{device.uuid}, " +
            "registrationToken=#{device.registrationToken}, " +
            "QRCode=#{device.QRCode}, " +
            "firebaseRegistrationToken=#{device.firebaseRegistrationToken}, " +
            "createAt=#{device.createAt}, " +
            "idClientFK=#{device.idClientFK} " +
            "WHERE idDeviceClient=#{device.idDeviceClient}")
    int putDevice(@Param("device") DeviceClient device);

    @Delete("DELETE FROM DeviceClient WHERE idDeviceClient=#{idDeviceClient}")
    int deleteDeviceByID(@Param("idDeviceClient") Integer idDeviceClient);

    @Select("SELECT * FROM DeviceClient WHERE registrationToken=#{registrationToken}")
    DeviceClient getDeviceByRegistrationToken(@Param("registrationToken") String registrationToken);

    @Select("SELECT * FROM DeviceClient WHERE qrcode=#{qrcode}")
    DeviceClient getDeviceByQRCode(@Param("qrcode") String qrcode);

    @Update("UPDATE DeviceClient SET " +
            "registrationToken=#{device.registrationToken}, " +
            "QRCode=#{device.QRCode}, " +
            "firebaseRegistrationToken=#{device.firebaseRegistrationToken}, " +
            "createAt=#{device.createAt}, " +
            "idClientFK=#{device.idClientFK} " +
            "WHERE uuid=#{device.uuid}")
    int updateDeviceByUUID(@Param("device") DeviceClient deviceClient);

    @Select("SELECT * FROM DeviceClient WHERE idClientFK=#{idClient}")
    List<DeviceClient> getClientDevices(@Param("idClient") Integer idClient);
}
