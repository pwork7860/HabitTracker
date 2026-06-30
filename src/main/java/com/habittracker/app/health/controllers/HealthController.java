package com.habittracker.app.health.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String checkHealth() {
        return "The App is Running";
    }
}
