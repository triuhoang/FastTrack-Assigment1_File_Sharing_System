package com.fsoft.filesharingbackend.config;

import com.fsoft.filesharingbackend.service.UserService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "security.config")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_ROOT_URL = "/api/**";
    private static final String[] PERMIT_ALL_URLS = new String[]{
            "/api/login"
    };

    private final SecurityConfigProperty securityConfigProperty;
    private final UserService userService;

    public WebSecurityConfig(SecurityConfigProperty securityConfigProperty, UserService userService) {
        this.securityConfigProperty = securityConfigProperty;
        this.userService = userService;
    }

    protected CorsFilterCustom buildCustomCorsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList(securityConfigProperty.getCors().getAllowedOrigin()));
        config.addAllowedHeader(
                securityConfigProperty.getCors().getAllowedHeader());
        config.setMaxAge(securityConfigProperty.getCors().getMaxAge());
        config.setAllowedMethods(Arrays
                .asList(securityConfigProperty.getCors().getAllowedMethod()));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(API_ROOT_URL, config);

        return new CorsFilterCustom(source);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PERMIT_ALL_URLS)
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(API_ROOT_URL).authenticated().and()
                .addFilter(buildCustomCorsFilter())
                .addFilterBefore(getPreAuthTokenHeaderFilter(), AbstractPreAuthenticatedProcessingFilter.class);
    }

    private PreAuthTokenHeaderFilter getPreAuthTokenHeaderFilter() {
        return new PreAuthTokenHeaderFilter(userService);
    }

}
