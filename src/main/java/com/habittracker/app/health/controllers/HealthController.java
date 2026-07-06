package com.habittracker.app.health.controllers;

import com.habittracker.app.health.dto.response.HealthCheckResponse;
import com.habittracker.app.health.svc.iface.HealthCheckSvc;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HealthController {

    private final HealthCheckSvc healthCheckSvc;

    @GetMapping("/")
    public HealthCheckResponse checkHealth() {
        return healthCheckSvc.getHalthStatus();
    }
}
