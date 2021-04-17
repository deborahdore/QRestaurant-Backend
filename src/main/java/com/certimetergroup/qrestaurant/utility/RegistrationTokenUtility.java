package com.certimetergroup.qrestaurant.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RegistrationTokenUtility {
    private final static Integer registrationTokenLength = 30;

    public static String generateRegistrationToken() {
        String randomString = RandomStringUtils.random(registrationTokenLength, true, true);
        return randomString + "_" + System.currentTimeMillis();
    }

    public static String generateQRCode(String UUID) {
        return UUID + "_" + System.currentTimeMillis();
    }
}
