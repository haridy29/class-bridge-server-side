package com.example.classbridge.controllers;

import com.example.classbridge.entities.Professor;
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

    @PostMapping("/addproffesor")
    public ResponseEntity<Professor> createProfessor(@RequestBody @Valid Professor professor) {
        Professor createdProfessor = professorService.createProfessor(professor.getUsername(), professor.getPassword());
        return ResponseEntity.ok(createdProfessor);
    }
}
