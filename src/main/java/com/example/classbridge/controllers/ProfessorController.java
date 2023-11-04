package com.example.classbridge.controllers;

import com.example.classbridge.entities.CourseClass;
import com.example.classbridge.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("professor")
public class ProfessorController {
    private final ProfessorService professorService;
    @GetMapping("course-classes")
    public List<CourseClass> getMyCourseClasses(Principal principal) {
        return professorService.getMyCourseClasses(principal.getName());
    }

}
