package com.certimetergroup.qrestaurant.client.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.Attendance;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.model.Notification;
import com.certimetergroup.qrestaurant.model.NotificationType;
import com.certimetergroup.qrestaurant.service.NotificationBaseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NotificationService extends NotificationBaseService {
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private Manager2SocietyService manager2SocietyService;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private DeviceManagerService deviceManagerService;
    @Autowired
    private NotificationTypeService notificationTypeService;

    @Value("${notification.client.infected}")
    private String clientInfected;
    @Value("${notification.client.infected.title}")
    private String title_infected;
    @Value("${notification.client.infected.body}")
    private String body_infected;

    private ObjectMapper mapper;
    private Logger logger;

    @PostConstruct
    public void init() {
        mapper = new ObjectMapper();
        logger = LoggerFactory.getLogger(this.getClass());
    }

    public void sendNotificationsClientInfected(Integer idClient, Timestamp selectPassedDate) {
        try {
            logger.info("SENDING NOTIFICATIONS");
            NotificationType notificationType = notificationTypeService.getNotificationType(clientInfected);

            List<Attendance> attendances = attendanceService.getClientAttendancesFrom(idClient, selectPassedDate);
            for (Attendance attendance : attendances) {
                List<Integer> managers = manager2SocietyService.getIdManagersOfSociety(attendance.getIdSocietyFK());
                for (Integer idManager : managers) {
                    Notification notification = new Notification(0, body_infected, notificationType.getIdNotificationType(), idManager, attendance.getIdAttendance());
                    this.insertNotification(notification);

                    List<DeviceManager> deviceManager = deviceManagerService.getDeviceManagerByIdManager(idManager);
                    List<String> tokens = deviceManager.stream().map(DeviceManager::getFirebaseRegistrationToken).filter(Objects::nonNull).collect(Collectors.toList());

                    if (tokens.size() > 0) {
                        logger.info("LIST OF FIREBASE REGISTRATION TOKENS TO SEND THE NOTIFICATION => ");
                        tokens.forEach(logger::info);

                        Map<String, String> params = new HashMap<>();
                        params.put("serviceId", notificationType.getIdNotificationType().toString());
                        params.put("payload", mapper.writeValueAsString(new Payload((attendance.getIdSocietyFK()))));
                        firebaseMessagingService.sendNotifications(title_infected, body_infected, tokens, params);
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new FailureException(ResponseType.FIREBASE_TOKEN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private class Payload {
        private final Integer idSociety;

        public Payload(Integer idSociety) {
            this.idSociety = idSociety;
        }

        public Integer getIdSociety() {
            return idSociety;
        }
    }
}
