package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Notification;
import com.certimetergroup.qrestaurant.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NotificationBaseService {

    @Autowired
    private NotificationRepository notificationRepository;

    /*** GET **/
    public Notification getNotification(Integer idNotification, Integer idManager) {
        Notification notification = notificationRepository.getNotification(idNotification, idManager);
        if (notification == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return notification;
    }

    /**
     * POST
     */
    public void insertNotification(Notification notification) {
        int rowsInserted = notificationRepository.postNotification(notification);
        if (rowsInserted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * PUT
     */
    public void updateNotification(Notification notification) {
        int rowsUpdated = notificationRepository.putNotification(notification);
        if (rowsUpdated < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    /**
     * DELETE
     */
    public void deleteNotification(Notification notification) {
        int rowsDeleted = notificationRepository.deleteNotification(notification);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

    public void deleteNotification(Integer idNotification) {
        int rowsDeleted = notificationRepository.deleteNotificationByID(idNotification);
        if (rowsDeleted < 1)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
    }

}
