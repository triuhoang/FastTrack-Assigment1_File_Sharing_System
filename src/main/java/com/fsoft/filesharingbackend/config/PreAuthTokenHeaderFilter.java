package com.fsoft.filesharingbackend.config;

import com.fsoft.filesharingbackend.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {
    private UserService userService;

    public PreAuthTokenHeaderFilter(UserService userService) {
        this.userService = userService;
        this.setAuthenticationManager(getNewAuthenticationManager());
    }

    private AuthenticationManager getNewAuthenticationManager() {
        return authentication -> {
            String principal = (String) authentication.getPrincipal();

            if (!userService.isVerified(principal)) {
                throw new BadCredentialsException("User is not verified! Please login!");
            }
            authentication.setAuthenticated(true);
            return authentication;
        };
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }
}