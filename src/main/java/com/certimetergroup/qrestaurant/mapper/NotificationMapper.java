package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.Notification;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Select("SELECT * FROM Notification WHERE idNotification=#{idNotification} and idManagerFK=#{idManager}")
    Notification getNotification(@Param("idNotification") Integer idNotification, @Param("idManager") Integer idManager);

    @Insert("INSERT INTO Notification(message, datetime, idNotificationTypeFK, idManagerFK, idAttendanceFK) " +
            "VALUES (#{notification.message}, #{notification.datetime}, #{notification.idNotificationTypeFK}, #{notification.idManagerFK}, #{notification.idAttendanceFK})")
    @Options(useGeneratedKeys = true, keyProperty = "idNotification", keyColumn = "idNotification")
    int postNotification(@Param("notification") Notification notification);


    @Update("UPDATE Notification SET message=#{notification.message}, datetime=#{notification.datetime}, " +
            "idNotificationTypeFK=#{notification.idNotificationTypeFK}, idManagerFK=#{notification.idManagerFK}, " +
            "idAttendanceFK=#{notification.idAttendanceFK} WHERE idNotification=#{notification.idNotification}")
    int putNotification(@Param("notification") Notification notification);

    @Delete("DELETE FROM Notification WHERE idNotification=#{notification.idNotification}")
    int deleteNotification(@Param("notification") Notification notification);

    @Delete("DELETE FROM Notification WHERE idNotification=#{idNotification}")
    int deleteNotificationByID(@Param("idNotification") Integer idNotification);

    @Select("SELECT * FROM Notification,Attendance WHERE Notification.idManagerFK=#{idManager} and " +
            "Notification.idAttendanceFK=Attendance.idAttendance and " +
            "Attendance.idSocietyFK=#{idSociety}")
    List<Notification> getAllNotifications(@Param("idManager") Integer idManager, @Param("idSociety") Integer idSociety);
}

