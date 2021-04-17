package com.certimetergroup.qrestaurant.security.constraints;

import java.util.Arrays;
import java.util.List;

public class EndpointContraints {

    public final static String MANAGER = "/**/manager/**";
    public final static String MANAGER_REGISTRATION_WEB = "/**/manager/web/registration";
    public final static String MANAGER_REGISTRATION_MOBILE = "/**/manager/mobile/registration";

    public final static String MANAGER_LOGIN_WEB = "/**/manager/web/login";
    public final static String MANAGER_LOGIN_MOBILE = "/**/manager/mobile/login";

    public final static String MANAGER_LOGIN_REFRESH_WEB = "/**/manager/web/login/refresh";
    public final static String MANAGER_LOGIN_REFRESH_MOBILE = "/**/manager/mobile/login/refresh";

    public final static String CLIENT = "/**/client/**";
    public final static String CLIENT_REGISTRATION_MOBILE = "/**/client/mobile/registration";

    public final static String ACTUATOR = "/**/actuator/**";

    /* Authorized endpoints  */
    public final static List<String> AUTHORIZED = Arrays.asList(
            MANAGER_LOGIN_MOBILE,
            MANAGER_LOGIN_WEB,
            MANAGER_REGISTRATION_WEB,
            MANAGER_REGISTRATION_MOBILE,
            MANAGER_LOGIN_REFRESH_WEB,
            MANAGER_LOGIN_REFRESH_MOBILE,
            CLIENT_REGISTRATION_MOBILE,
            ACTUATOR
    );
}
