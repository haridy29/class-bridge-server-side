package com.example.classbridge.repositories;

import com.example.classbridge.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, String> {
    <Optional> Professor findByUsername(String username);

}
