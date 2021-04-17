package com.certimetergroup.qrestaurant.repository;

import com.certimetergroup.qrestaurant.mapper.NotificationMapper;
import com.certimetergroup.qrestaurant.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationRepository {
    @Autowired
    private NotificationMapper notificationMapper;

    public Notification getNotification(Integer idNotification, Integer idManager) {
        return notificationMapper.getNotification(idNotification, idManager);
    }

    public List<Notification> getAllNotifications(Integer idManager, Integer idSociety) {
        return notificationMapper.getAllNotifications(idManager, idSociety);
    }

    public int postNotification(Notification notification) {
        return notificationMapper.postNotification(notification);
    }

    public int putNotification(Notification notification) {
        return notificationMapper.putNotification(notification);
    }

    public int deleteNotification(Notification notification) {
        return notificationMapper.deleteNotification(notification);
    }

    public int deleteNotificationByID(Integer idNotification) {
        return notificationMapper.deleteNotificationByID(idNotification);
    }


}
