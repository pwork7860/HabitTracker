package com.habittracker.app.user.controller;

import com.habittracker.app.commons.dto.response.ApiResponse;
import com.habittracker.app.user.data.dto.requests.CreateUserReq;
import com.habittracker.app.user.data.dto.requests.UpdatePasswordReq;
import com.habittracker.app.user.data.dto.requests.UpdateUserReq;
import com.habittracker.app.user.data.dto.response.CreateUserRes;
import com.habittracker.app.user.data.dto.response.PasswordUpdateReponse;
import com.habittracker.app.user.data.dto.response.UpdateUserRes;
import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import com.habittracker.app.user.service.iface.UserSvc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserSvc userSvc;

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<CreateUserRes>> createUser(
            @Valid @RequestBody CreateUserReq req) {
        CreateUserRes createuserRes =  userSvc.createUser(req);
        URI uri = URI.create("v1/user/" + createuserRes.getId());
        return ResponseEntity.created(uri).body(ApiResponse.success(
                "User Created Successfully", createuserRes));
    }

    @GetMapping("user")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> getUserById() {
        UserDetailsResponse userDetailsResponse = userSvc.getUser();
        return ResponseEntity.ok(ApiResponse.success(
                "User Featched Successfully", userDetailsResponse));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDetailsResponse>>> getAllUser() {
        List<UserDetailsResponse> userDetailsResponses = userSvc.getAllusers();
        return ResponseEntity
                .ok(ApiResponse.success("All Users List", userDetailsResponses));
    }


    @PutMapping("user/{userid}")
    public ResponseEntity<ApiResponse<UpdateUserRes>> updateUser(
            @Valid @RequestBody UpdateUserReq req) {
        UpdateUserRes updateUserRes = userSvc.updateUser(req);
        return ResponseEntity
                .ok(ApiResponse.success("User Updated Successfully ",
                        updateUserRes));
    }


    @DeleteMapping("user")
    public ResponseEntity<ApiResponse<String>> deleteUser() {
        userSvc.deleteUser();
        return ResponseEntity
                .ok(ApiResponse.success("User Deleted Successfully ", "success"));
    }

    @PutMapping("password")
    public ResponseEntity<ApiResponse<String>> updatePassword(
            @Valid @RequestBody UpdatePasswordReq req,
            @PathVariable("userid") String userId) {
        PasswordUpdateReponse passwordUpdateReponse = userSvc.updatePassword(req, userId);
        return ResponseEntity
                .ok(ApiResponse.success("Password Updated Successfully ", userId));
    }

}
