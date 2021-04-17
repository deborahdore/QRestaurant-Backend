package com.certimetergroup.qrestaurant.security.service;

import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.security.exception.AuthenticationErrException;
import com.certimetergroup.qrestaurant.service.JWTService;
import com.certimetergroup.qrestaurant.utility.TokenUtility;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class JwtSecurityService extends JWTService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${authentication.jwt.private.key}")
    private String privateKey;

    public void validateAccessTokenSecurity(String accessToken) {
        try {
            Jwts.parser().setSigningKey(privateKey).parseClaimsJws(TokenUtility.cleanAccessToken(accessToken));
        } catch (ExpiredJwtException f) {
            logger.warn("EXPIRED ACCESS TOKEN: " + accessToken);
            throw new AuthenticationErrException(AuthenticationErrType.EXPIRED_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (SignatureException e) {
            logger.warn("INVALID ACCESS TOKEN: " + accessToken);
            throw new AuthenticationErrException(AuthenticationErrType.INVALID_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (MalformedJwtException e) {
            logger.error("MALFORMED TOKEN" + accessToken);
            throw new AuthenticationErrException(AuthenticationErrType.MALFORMED_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            logger.error("ERROR", e);
            throw new AuthenticationErrException(AuthenticationErrType.UNEXPECTED_ERROR, HttpStatus.UNAUTHORIZED);
        }
    }
}
