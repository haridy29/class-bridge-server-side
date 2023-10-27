package com.example.classbridge.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "username should not be blank")
    @NotNull(message = "username should not be null")
    private String username;
    @NotBlank(message = "password should not be blank")
    @NotNull(message = "password should not be null")
    private String password;
}
