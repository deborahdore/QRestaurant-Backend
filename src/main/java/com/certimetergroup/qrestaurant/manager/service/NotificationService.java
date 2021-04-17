package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.DeviceClient;
import com.certimetergroup.qrestaurant.model.Notification;
import com.certimetergroup.qrestaurant.model.NotificationType;
import com.certimetergroup.qrestaurant.repository.NotificationRepository;
import com.certimetergroup.qrestaurant.service.NotificationBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService extends NotificationBaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private Manager2SocietyService manager2SocietyService;
    @Autowired
    private NotificationTypeService notificationTypeService;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @Value("${notification.client.qrcode}")
    private String qrcodeChanged;
    @Value("${notification.client.qrcode.changed.title}")
    private String title_qrcode;
    @Value("${notification.client.qrcode.changed.body}")
    private String body_qrcode;

    public Notification getExistingNotification(Integer idNotification, Integer idManager) {
        Notification notification = notificationRepository.getNotification(idNotification, idManager);
        if (notification == null)
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        return notification;
    }

    public Map<Notification, Attendance> getAllNotifications(Integer idManager, Integer idSociety) {
        if (!manager2SocietyService.isManagerSociety(idManager, idSociety))
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        Map<Notification, Attendance> notificationSocietyMap = new HashMap<>();
        List<Notification> notificationList = notificationRepository.getAllNotifications(idManager, idSociety);
        notificationList.forEach(notification -> {
            Integer idAttendance = notification.getIdAttendanceFK();
            notificationSocietyMap.put(notification, attendanceService.getAttendance(idAttendance));
        });
        return notificationSocietyMap;
    }

    public void sendNotificationQrCodeChanged(DeviceClient deviceClient, String societyName) {
        logger.info("SENDING NOTIFICATIONS");
        NotificationType notificationType = notificationTypeService.getNotificationType(qrcodeChanged);

        if (deviceClient.getFirebaseRegistrationToken() != null) {
            logger.info("LIST OF FIREBASE REGISTRATION TOKENS TO SEND THE NOTIFICATION => " + deviceClient.getFirebaseRegistrationToken());
            Map<String, String> params = new HashMap<>();
            body_qrcode = String.format(body_qrcode, societyName);
            params.put("serviceId", notificationType.getIdNotificationType().toString());
            firebaseMessagingService.sendNotification(title_qrcode, body_qrcode, deviceClient.getFirebaseRegistrationToken(), params);
        }
    }
}
