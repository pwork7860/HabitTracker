package com.habittracker.app.login.service.iface;

import com.habittracker.app.login.data.dto.request.GoogleloginRequest;
import com.habittracker.app.login.data.dto.request.LoginRequest;
import com.habittracker.app.login.data.dto.response.LoginResponse;

public interface LoginSvc {

    LoginResponse login(LoginRequest request);

    LoginResponse googleLogin(GoogleloginRequest request);

}
