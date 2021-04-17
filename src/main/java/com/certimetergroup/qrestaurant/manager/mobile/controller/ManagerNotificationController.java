package com.certimetergroup.qrestaurant.manager.mobile.controller;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.manager.response.GetAllNotificationsResponse;
import com.certimetergroup.qrestaurant.manager.service.ManagerJwtService;
import com.certimetergroup.qrestaurant.manager.service.NotificationService;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.Notification;
import com.certimetergroup.qrestaurant.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Validated
@RestController
public class ManagerNotificationController {
    @Autowired
    private ManagerJwtService jwtService;
    @Autowired
    private NotificationService notificationService;

    /*
     * ENDPOINT USED TO GET THE LIST OF NOTIFICATION OF A MANAGER FOR A SPECIFIC SOCIETY
     * @param accessToken is manager's JWT access token
     * @param idSociety contains the society's id
     * @return list of notifications
     */
    @GetMapping("/notification/society/{idSociety}")
    public ResponseEntity<Response> getNotification(@RequestHeader("Authorization") @NotEmpty String accessToken,
                                                    @PathVariable @NotNull Integer idSociety) {

        Integer idManager = jwtService.getFieldFromAccessToken(accessToken, "id_user", Integer.class);
        Map<Notification, Attendance> notifications = notificationService.getAllNotifications(idManager, idSociety);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new GetAllNotificationsResponse(ResponseType.SUCCESS, notifications));
    }
}
