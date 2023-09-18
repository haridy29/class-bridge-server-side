package com.example.classbridge.services;

import com.example.classbridge.entities.Professor;
import com.example.classbridge.entities.Profile;
import com.example.classbridge.repositories.ProfessorRepository;
import com.example.classbridge.utilities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Professor createProfessor(String username, String password) {

        try {
            Professor newProfessor = new Professor();
            newProfessor.setUsername(username);
            String encodedPassword = passwordEncoder.encode(password);
            newProfessor.setPassword(encodedPassword);
            newProfessor.setRole(Role.ROLE_PROFESSOR);
            Profile profile = new Profile();
            newProfessor.setProfile(profile);
            return professorRepository.save(newProfessor);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Failed to create new Professor ! " + ex.getMessage());
        }
    }
}
