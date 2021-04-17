package com.certimetergroup.qrestaurant.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfiguration {
    @Value("${app.firebase.config.business}")
    private String firebaseConfigBusiness;
    @Value("${app.firebase.config.client}")
    private String firebaseConfigClient;

    @Bean(name = "firebaseMessaggingBusiness")
    public FirebaseMessaging firebaseMessagingBusiness() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigBusiness).getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "qrestaurant-business");
        return FirebaseMessaging.getInstance(app);
    }

    @Bean(name = "firebaseMessaggingClient")
    public FirebaseMessaging firebaseMessagingClient() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigClient).getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "qrestaurant");
        return FirebaseMessaging.getInstance(app);
    }
}
