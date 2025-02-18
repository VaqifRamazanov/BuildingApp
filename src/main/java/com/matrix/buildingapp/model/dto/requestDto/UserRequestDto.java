package com.matrix.buildingapp.model.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserRequestDto {
    @NotBlank(message = "Username or email cannot be empty or null")
    @Size(max = 50)
    @Pattern(regexp = "[A-Za-z0-9_.@]+$")
    private String username;
    @Email
    private String email;
    @NotBlank(message = "Password cannot be empty or null")
    @Pattern(regexp = "[A-Za-z0-9_.@]+$", message="the value must be positive integer")
    @Size(min = 8)
    private String password;

}
