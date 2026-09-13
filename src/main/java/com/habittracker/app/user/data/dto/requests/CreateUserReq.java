package com.habittracker.app.user.data.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserReq {
    private String Id;
    @NotBlank
    private String name;
    private String profileImgUrl;
    @Email
    private String emailId;
    private String phoneNumber;
    private String password;
    private String timeZone;

}
