package com.certimetergroup.qrestaurant.security.provider;

import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.security.authentication.JwtAuthentication;
import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.security.exception.AuthenticationErrException;
import com.certimetergroup.qrestaurant.security.service.JwtSecurityService;
import com.certimetergroup.qrestaurant.service.ManagerBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTAuthProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private JwtSecurityService jwtService;
    @Autowired
    private ManagerBaseService managerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationErrException {
        logger.info("JWT PROVIDER");
        Object accessToken = authentication.getPrincipal();
        if (!accessToken.getClass().equals(String.class))
            throw new AuthenticationErrException(AuthenticationErrType.INVALID_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);

        jwtService.validateAccessTokenSecurity((String) accessToken);

        Manager manager = managerService.getManager(jwtService.getFieldFromAccessToken((String) accessToken, "id_user", Integer.class));
        if (manager == null)
            throw new AuthenticationErrException(AuthenticationErrType.INVALID_LOGIN, HttpStatus.UNAUTHORIZED);

        String phone = jwtService.getFieldFromAccessToken((String) accessToken, "phone", String.class);
        if (!manager.getPhone().equals(phone))
            throw new AuthenticationErrException(AuthenticationErrType.INVALID_LOGIN, HttpStatus.UNAUTHORIZED);

        /* create new token of type UsernamePasswordAuthenticationToken */
        UserDetails userDetails = new User(manager.getPhone(), manager.getPassword(), new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


    /* this provider supports Authentication Token of type JWT Token */
    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.equals(aClass);
    }
}
