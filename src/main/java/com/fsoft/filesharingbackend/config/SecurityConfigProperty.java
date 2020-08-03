package com.fsoft.filesharingbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security")
public class SecurityConfigProperty {
    private final Cors cors = new Cors();

    public Cors getCors() {
        return cors;
    }

    public static class Cors {
        private String allowedOrigin;
        private String allowedHeader;
        private Long maxAge;
        private String[] allowedMethod;

        public String getAllowedOrigin() {
            return allowedOrigin;
        }

        public void setAllowedOrigin(String allowedOrigin) {
            this.allowedOrigin = allowedOrigin;
        }

        public String getAllowedHeader() {
            return allowedHeader;
        }

        public void setAllowedHeader(String allowedHeader) {
            this.allowedHeader = allowedHeader;
        }

        public Long getMaxAge() {
            return maxAge;
        }

        public void setMaxAge(Long maxAge) {
            this.maxAge = maxAge;
        }

        public String[] getAllowedMethod() {
            return allowedMethod;
        }

        public void setAllowedMethod(String[] allowedMethod) {
            this.allowedMethod = allowedMethod;
        }
    }
}
