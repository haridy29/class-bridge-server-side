package com.example.classbridge.controllers;

import com.example.classbridge.dtos.LoginDto;
import com.example.classbridge.services.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(HttpServletResponse response, @Valid @RequestBody LoginDto loginDTO) {
        try {
            service.login(response, loginDTO);
            return ResponseEntity.ok("Success");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid username or password");
        }
    }

}
