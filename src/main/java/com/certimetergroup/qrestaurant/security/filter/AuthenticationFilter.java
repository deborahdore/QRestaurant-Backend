package com.certimetergroup.qrestaurant.security.filter;

import com.certimetergroup.qrestaurant.security.authentication.JwtAuthentication;
import com.certimetergroup.qrestaurant.security.authentication.TokenAuthentication;
import com.certimetergroup.qrestaurant.security.enumeration.AuthenticationErrType;
import com.certimetergroup.qrestaurant.security.exception.AuthenticationErrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.certimetergroup.qrestaurant.security.constraints.EndpointContraints.*;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private List<RequestMatcher> permitAllWithoutAuthUrl;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void init() {
        permitAllWithoutAuthUrl = new ArrayList<>();
        AUTHORIZED.forEach(s -> permitAllWithoutAuthUrl.add(new AntPathRequestMatcher(s)));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            logger.info("AUTHENTICATING REQUESTS..");
            Authentication auth = null;
            /* if request matches manager's endpoint */
            if (new AntPathRequestMatcher(MANAGER).matches(request)) {
                String jwt = request.getHeader("Authorization");
                if (jwt == null) {
                    throw new AuthenticationErrException(AuthenticationErrType.INVALID_ACCESS_TOKEN, HttpStatus.UNAUTHORIZED);
                }
                /* set Authorization as jwt token */
                auth = new JwtAuthentication(jwt, new ArrayList<>());

            } else if (new AntPathRequestMatcher(CLIENT).matches(request)) {  /* if request matches client's endpoint */
                String clientTkn = request.getHeader("Authorization-Client-token");
                if (clientTkn == null) {
                    throw new AuthenticationErrException(AuthenticationErrType.INVALID_REGISTRATION_TOKEN, HttpStatus.UNAUTHORIZED);
                }
                /* set Authorization as registration token */
                auth = new TokenAuthentication(clientTkn, new ArrayList<>());
            }

            /* try to validate token and set it in the context */
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(auth));
        } catch (AuthenticationErrException e) {
            this.setRequestAttribute(request, e.getType(), e.getStatus());
            e.printStackTrace();
        }
        chain.doFilter(request, response);
    }


    /* This filter should not filter the endpoints that are authorized */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return this.isAuthorized(request);
    }

    public boolean isAuthorized(HttpServletRequest req) {
        for (RequestMatcher url : permitAllWithoutAuthUrl) {
            if (url.matches(req))
                return true;
        }
        return false;
    }

    public void setRequestAttribute(HttpServletRequest request, AuthenticationErrType responseType, HttpStatus httpStatus) {
        request.setAttribute("response_type", responseType);
        request.setAttribute("http_status", httpStatus);
    }
}
