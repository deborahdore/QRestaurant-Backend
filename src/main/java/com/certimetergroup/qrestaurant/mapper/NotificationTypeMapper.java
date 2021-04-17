package com.certimetergroup.qrestaurant.mapper;

import com.certimetergroup.qrestaurant.model.NotificationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NotificationTypeMapper {

    @Select("SELECT * FROM NotificationType WHERE idNotificationType=#{idNotificationType}")
    NotificationType getNotificationType(@Param("idNotificationType") Integer idNotificationType);

    @Select("SELECT * FROM NotificationType WHERE type=#{type}")
    NotificationType getNotificationTypeByType(@Param("type") String type);
}
