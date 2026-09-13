package com.habittracker.app.user.data.dto.requests;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserReq {
    private String name;
    @Email
    private String emailId;
    private String phoneNumber;
    private String profileImgUrl;
    private String timeZone;
}
