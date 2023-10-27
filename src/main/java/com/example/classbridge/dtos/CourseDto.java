package com.example.classbridge.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    @NotNull(message = "Course name should not be null")
    @NotBlank(message = "Course name should not be blank")
    private String courseName;

    private List<String>prerequisiteNames;

}
