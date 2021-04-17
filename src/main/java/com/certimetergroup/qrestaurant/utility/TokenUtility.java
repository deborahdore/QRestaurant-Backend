package com.certimetergroup.qrestaurant.utility;

public class TokenUtility {
    public static String cleanAccessToken(String token) {
        return token.replace("Bearer", "").trim();
    }
}
