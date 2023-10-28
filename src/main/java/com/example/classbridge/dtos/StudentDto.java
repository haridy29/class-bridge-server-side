package com.example.classbridge.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    @NotNull(message = "user data should not be null")
    @Valid
    private UserDto userDto;
    @NotBlank(message = "student ID must not be blank")
    private String studId;

}
