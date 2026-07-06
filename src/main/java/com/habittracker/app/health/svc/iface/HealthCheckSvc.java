package com.habittracker.app.health.svc.iface;

import com.habittracker.app.health.dto.response.HealthCheckResponse;

public interface HealthCheckSvc {

    HealthCheckResponse getHalthStatus();
}
