package com.certimetergroup.qrestaurant.manager.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FirebaseMessagingService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FirebaseMessaging firebaseMessaggingClient;

    /* Manager sends notification to clients when they visit the restaurant */
    public void sendNotification(String title, String body, String token, Map<String, String> params) {
        try {
            Notification firebaseNotification = Notification
                    .builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            Message message = Message
                    .builder()
                    .setToken(token)
                    .putAllData(params)
                    .setNotification(firebaseNotification)
                    .build();

            if (firebaseMessaggingClient.send(message) == null)
                throw new FailureException(ResponseType.FIREBASE_TOKEN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (FirebaseMessagingException e) {

            throw new FailureException(ResponseType.FIREBASE_TOKEN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void sendNotifications(String title, String body, List<String> tokens, Map<String, String> params) {
        try {
            Notification firebaseNotification = Notification
                    .builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            MulticastMessage message = MulticastMessage
                    .builder()
                    .addAllTokens(tokens)
                    .setNotification(firebaseNotification)
                    .putAllData(params)
                    .build();
            BatchResponse response = firebaseMessaggingClient.sendMulticast(message);
            if (response == null) {
                throw new FailureException(ResponseType.FIREBASE_TOKEN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (response.getFailureCount() > 0)
                logger.warn("COULD NOT SEND FIREBASE NOTIFICATION!");
        } catch (FirebaseMessagingException e) {
            throw new FailureException(ResponseType.FIREBASE_TOKEN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
