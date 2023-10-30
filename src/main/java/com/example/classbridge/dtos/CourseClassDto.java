package com.example.classbridge.dtos;

import com.example.classbridge.utilities.Semester;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseClassDto {
    @NotBlank(message = "Course name must not be blank")
    private String courseName;

    @NotBlank(message = "Professor name must not be blank")
    private String professorName;

    @NotNull(message = "Year must not be null")
    @Min(value = 1900,message = "Year must be greater than or equal to 1900")
    @Max(value = 2099,message = "Year must be less than or equal to 2099")
    private Integer year;

    @NotNull(message = "Semester must not null")
    @Enumerated(EnumType.STRING)
    private Semester semester;

}
