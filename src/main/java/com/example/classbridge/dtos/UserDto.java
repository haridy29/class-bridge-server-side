package com.example.classbridge.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull(message = "profile data must not be null")
    @Valid
    private ProfileDto profileDto;
    @NotBlank(message = "username must not be blank")
    private String username;
    @NotBlank(message = "Password must not be blank")
    @Length(min=8,max=32,message="The minimum password length is 8 and the max is 32")
    private String password;

}
