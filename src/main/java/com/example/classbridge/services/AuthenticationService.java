package com.example.classbridge.services;

import com.example.classbridge.dtos.LoginDto;
import com.example.classbridge.repositories.UserRepo;
import com.example.classbridge.config.security.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepo userRepo;

    public void login(HttpServletResponse response, LoginDto loginDTO) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        var user = userRepo.findByUsername(loginDTO.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        response.addHeader("Authorization", jwtToken);
    }
}
