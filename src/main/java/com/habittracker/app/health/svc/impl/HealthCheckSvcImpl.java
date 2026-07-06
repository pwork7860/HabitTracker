package com.habittracker.app.health.svc.impl;

import com.habittracker.app.health.dto.response.HealthCheckResponse;
import com.habittracker.app.health.svc.iface.HealthCheckSvc;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckSvcImpl implements HealthCheckSvc {
    @Override
    public HealthCheckResponse getHalthStatus() {
        return HealthCheckResponse
                .builder()
                .response("Backend is Running")
                .status("ACTIVE")
                .build();
    }
}
