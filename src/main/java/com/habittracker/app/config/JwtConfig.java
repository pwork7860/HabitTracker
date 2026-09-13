package com.habittracker.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("jwt")
@Data
@Component
public class JwtConfig {

    private String secret;
    private long expiraionTime;
}
