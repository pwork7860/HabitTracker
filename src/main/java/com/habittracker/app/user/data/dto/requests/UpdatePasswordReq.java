package com.habittracker.app.user.data.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordReq {
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;
}
