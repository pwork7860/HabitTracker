package com.habittracker.app.login.controller;

import com.habittracker.app.commons.dto.response.ApiResponse;
import com.habittracker.app.login.data.dto.request.GoogleloginRequest;
import com.habittracker.app.login.data.dto.request.LoginRequest;
import com.habittracker.app.login.data.dto.response.LoginResponse;
import com.habittracker.app.login.service.iface.LoginSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginSvc loginSvc;

    @PostMapping
    public ResponseEntity<ApiResponse<LoginResponse>> adminLogin(
            @Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginSvc.login(request);
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Login Successfull", response));
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<LoginResponse>> Login(
            @Valid @RequestBody GoogleloginRequest request) {
        LoginResponse response = loginSvc.googleLogin(request);
        return ResponseEntity.ok(
                ApiResponse.success(
                        "Login Successfull", response));
    }

}
