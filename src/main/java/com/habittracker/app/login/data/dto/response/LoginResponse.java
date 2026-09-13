package com.habittracker.app.login.data.dto.response;

import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private UserDetailsResponse userDetailsResponse;
}
