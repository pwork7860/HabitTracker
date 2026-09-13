package com.habittracker.app.user.service.iface;

import com.habittracker.app.user.data.dto.requests.CreateUserReq;
import com.habittracker.app.user.data.dto.requests.UpdatePasswordReq;
import com.habittracker.app.user.data.dto.requests.UpdateUserReq;
import com.habittracker.app.user.data.dto.response.CreateUserRes;
import com.habittracker.app.user.data.dto.response.PasswordUpdateReponse;
import com.habittracker.app.user.data.dto.response.UpdateUserRes;
import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import com.habittracker.app.user.data.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserSvc {
    CreateUserRes createUser(CreateUserReq req);

    UserDetailsResponse getUser();

    User getUserbyEmailid(String emailId);

    List<UserDetailsResponse> getAllusers();

    UpdateUserRes updateUser(UpdateUserReq req);

    void deleteUser();

    PasswordUpdateReponse updatePassword(UpdatePasswordReq req, String userId);
}
