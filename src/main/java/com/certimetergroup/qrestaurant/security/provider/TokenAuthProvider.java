package com.certimetergroup.qrestaurant.security.provider;

import com.certimetergroup.qrestaurant.security.authentication.TokenAuthentication;
import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.security.exception.AuthenticationErrException;
import com.certimetergroup.qrestaurant.security.service.RegistrationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RegistrationTokenService registrationTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationErrException {
        logger.info("REGISTRATION TOKEN PROVIDER");
        Object registrationTkn = authentication.getPrincipal();
        if (!registrationTkn.getClass().equals(String.class))
            throw new AuthenticationErrException(AuthenticationErrType.INVALID_REGISTRATION_TOKEN, HttpStatus.UNAUTHORIZED);

        registrationTokenService.validateRegistrationToken((String) registrationTkn);
        return authentication;
    }

    /* this provider supports Authentication Token of type Registration Token */
    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
