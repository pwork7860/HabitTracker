package com.habittracker.app.health.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Data
@Builder
public class HealthCheckResponse {
    private String response;
    private String status;
}
