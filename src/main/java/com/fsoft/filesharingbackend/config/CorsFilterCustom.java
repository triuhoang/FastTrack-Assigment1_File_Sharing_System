package com.fsoft.filesharingbackend.config;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class CorsFilterCustom extends CorsFilter {
    public CorsFilterCustom(UrlBasedCorsConfigurationSource configSource) {
        super(configSource);
    }
}
