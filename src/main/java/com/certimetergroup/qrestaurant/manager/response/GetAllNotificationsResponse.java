package com.certimetergroup.qrestaurant.manager.response;

import com.certimetergroup.qrestaurant.dto.DTONotification;
import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Notification;
import com.certimetergroup.qrestaurant.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetAllNotificationsResponse extends Response {
    private List<DTONotification> notifications;
    private Integer numNotifications;

    public GetAllNotificationsResponse(ResponseType response, Map<Notification, Attendance> notificationsMap) {
        super(response);
        this.notifications = new ArrayList<>();
        notificationsMap.forEach(((notification, attendance) -> {
            DTONotification dtoNotification = notification.toDTO();
            dtoNotification.setAttendance(attendance.toDTO());
            this.notifications.add(dtoNotification);
        }));
        this.numNotifications = notifications.size();
    }

    public List<DTONotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<DTONotification> notifications) {
        this.notifications = notifications;
    }

    public Integer getNumNotifications() {
        return numNotifications;
    }

    public void setNumNotifications(Integer numNotifications) {
        this.numNotifications = numNotifications;
    }
}
