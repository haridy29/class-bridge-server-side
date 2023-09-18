package com.example.classbridge.entities;

import com.example.classbridge.utilities.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_professor")
public class Professor extends User {

    public Professor(String username, String password, Profile profile) {
        super(username, password, Role.ROLE_PROFESSOR, profile);
    }

    public Professor() {
        this.setRole(Role.ROLE_PROFESSOR);
    }
}
