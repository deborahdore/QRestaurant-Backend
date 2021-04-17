package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.NotificationType;
import com.certimetergroup.qrestaurant.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NotificationTypeBaseService {
    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    /*** GET ***/
    public NotificationType getNotificationType(Integer idNotificationType) {
        NotificationType notificationType = notificationTypeRepository.getNotificationType(idNotificationType);
        if (notificationType == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return notificationType;
    }

    /*** GET ***/
    public NotificationType getNotificationType(String type) {
        NotificationType notificationType = notificationTypeRepository.getNotificationTypeByType(type);
        if (notificationType == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return notificationType;
    }
}
