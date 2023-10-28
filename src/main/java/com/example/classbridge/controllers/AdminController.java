package com.example.classbridge.controllers;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseDto;
import com.example.classbridge.dtos.StudentDto;
import com.example.classbridge.entities.Course;
import com.example.classbridge.entities.Professor;
import com.example.classbridge.entities.Student;
import com.example.classbridge.services.CourseService;
import com.example.classbridge.services.ProfessorService;
import com.example.classbridge.services.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ProfessorService professorService;
    private final CourseService courseService;
    private final StudentService studentService;

    @PostMapping("/professor")
    public ResponseEntity<Professor> createProfessor(@RequestBody @Valid Professor professor) {
        Professor createdProfessor = professorService.createProfessor(professor.getUsername(), professor.getPassword());
        return ResponseEntity.ok(createdProfessor);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody @Valid CourseDto courseDto) throws BadRequestException {
        return courseService.addCourse(courseDto);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> creatStudent(@Valid @RequestBody StudentDto studentDto) throws BadRequestException {
        return studentService.creatStudent(studentDto);
    }
}
