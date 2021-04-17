package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.NotificationTypeMapper;
import com.certimetergroup.qrestaurant.model.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationTypeRepository {
    @Autowired
    private NotificationTypeMapper notificationTypeMapper;

    public NotificationType getNotificationType(Integer idNotificationType) {
        return notificationTypeMapper.getNotificationType(idNotificationType);
    }

    public NotificationType getNotificationTypeByType(String type) {
        return notificationTypeMapper.getNotificationTypeByType(type);
    }
}
