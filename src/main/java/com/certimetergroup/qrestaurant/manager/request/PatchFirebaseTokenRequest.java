package com.certimetergroup.qrestaurant.manager.request;

import javax.validation.constraints.NotEmpty;

public class PatchFirebaseTokenRequest {
    @NotEmpty
    private String firebaseRegistrationToken;

    public PatchFirebaseTokenRequest(@NotEmpty String firebaseRegistrationToken) {
        this.firebaseRegistrationToken = firebaseRegistrationToken;
    }

    public PatchFirebaseTokenRequest() {
    }

    public String getFirebaseRegistrationToken() {
        return firebaseRegistrationToken;
    }

    public void setFirebaseRegistrationToken(String firebaseRegistrationToken) {
        this.firebaseRegistrationToken = firebaseRegistrationToken;
    }
}
