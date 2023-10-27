package com.example.classbridge.controllers;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseDto;
import com.example.classbridge.entities.Course;
import com.example.classbridge.entities.Professor;
import com.example.classbridge.services.CourseService;
import com.example.classbridge.services.ProfessorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProfessorService professorService;
    private final PasswordEncoder passwordEncoder;
    private final CourseService courseService;

    @PostMapping("/addproffesor")
    public ResponseEntity<Professor> createProfessor(@RequestBody @Valid Professor professor) {
        Professor createdProfessor = professorService.createProfessor(professor.getUsername(), professor.getPassword());
        return ResponseEntity.ok(createdProfessor);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody @Valid CourseDto courseDto) throws BadRequestException {
        return courseService.addCourse(courseDto);
    }
}
