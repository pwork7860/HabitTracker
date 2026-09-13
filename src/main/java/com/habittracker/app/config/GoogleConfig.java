package com.habittracker.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("google")
public class GoogleConfig {
    private String clientId;
    private String clientSecret;
    private String projectId;
}
