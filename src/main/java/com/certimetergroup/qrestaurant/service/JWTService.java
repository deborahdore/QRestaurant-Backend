package com.certimetergroup.qrestaurant.service;

import com.certimetergroup.qrestaurant.enumeration.ResponseType;
import com.certimetergroup.qrestaurant.exception.FailureException;
import com.certimetergroup.qrestaurant.model.DeviceManager;
import com.certimetergroup.qrestaurant.model.Manager;
import com.certimetergroup.qrestaurant.utility.TokenUtility;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    @Value("${authentication.jwt.expiration.time}")
    private long EXPIRATION_TIME;
    @Value("${authentication.jwt.private.key}")
    private String privateKey;
    @Value("${authentication.jwt.public.key}")
    private String publicKey;

    public String generateRefreshToken(Manager manager, String UUID) {
        String jwt = Jwts
                .builder()
                .signWith(signatureAlgorithm, publicKey)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .claim("id_user", manager.getIdManager())
                .claim("phone", manager.getPhone())
                .claim("UUID", UUID)
                .compact();
        return jwt;

    }

    public String generateAccessToken(Manager manager, String UUID) {
        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(EXPIRATION_TIME + System.currentTimeMillis());

        String jwt = Jwts
                .builder()
                .signWith(signatureAlgorithm, privateKey)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .claim("id_user", manager.getIdManager())
                .claim("phone", manager.getPhone())
                .claim("UUID", UUID)
                .compact();
        return jwt;
    }

    public void validateRefreshToken(String refreshToken, DeviceManager manager) {
        try {
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(refreshToken);
            if (!manager.getRefreshToken().equals(refreshToken))
                throw new FailureException(ResponseType.INVALID_REFRESH_TOKEN, HttpStatus.UNAUTHORIZED);

        } catch (SignatureException e) {
            logger.warn("INVALID ACCESS TOKEN: " + refreshToken);
            throw new FailureException(ResponseType.INVALID_REFRESH_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            logger.error("MALFORMED TOKEN" + refreshToken);
            throw new FailureException(ResponseType.MALFORMED_REFRESH_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("ERROR", e);
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        }
    }


    public <T> T getFieldFromAccessToken(String accessToken, String fieldName, Class<T> fieldClass) {
        return Jwts.parser().setSigningKey(privateKey).parseClaimsJws(TokenUtility.cleanAccessToken(accessToken)).getBody().get(fieldName, fieldClass);
    }

    public <T> T getFieldFromRefreshToken(String refreshToken, String fieldName, Class<T> fieldClass) {
        try {
            return Jwts.parser().
                    setSigningKey(publicKey).
                    parseClaimsJws(refreshToken).
                    getBody().get(fieldName, fieldClass);
        } catch (MalformedJwtException e) {
            logger.error("###   REFRESH TOKEN INVALID " + refreshToken);
            throw new FailureException(ResponseType.INVALID_REFRESH_TOKEN, HttpStatus.UNAUTHORIZED);
        }
    }

    public void validateAccessToken(String accessToken) {
        try {
            Jwts.parser().setSigningKey(privateKey).parseClaimsJws(TokenUtility.cleanAccessToken(accessToken));
        } catch (ExpiredJwtException f) {
            logger.warn("###   EXPIRED ACCESS TOKEN: " + accessToken);
            throw new FailureException(ResponseType.EXPIRED_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (SignatureException e) {
            logger.warn("###   INVALID ACCESS TOKEN: " + accessToken);
            throw new FailureException(ResponseType.INVALID_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            logger.error("###   MALFORMED TOKEN" + accessToken);
            throw new FailureException(ResponseType.MALFORMED_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("ERROR", e);
            throw new FailureException(ResponseType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        }
    }

    public void isAccessTokenExpired(String accessToken) {
        try {
            this.validateAccessToken(accessToken);
            throw new FailureException(ResponseType.ACCESS_TOKEN_STILL_VALID, HttpStatus.OK);
        } catch (FailureException e) {
            if (!e.getType().equals(ResponseType.EXPIRED_ACCESS_TOKEN))
                throw e;
        }
    }
}
