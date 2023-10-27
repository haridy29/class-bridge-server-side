package com.example.classbridge.entities;


import com.example.classbridge.utilities.Role;
import jakarta.persistence.Entity;

@Entity
public class Admin extends User {
    public Admin() {
        super.setRole(Role.ROLE_ADMIN);
    }

    public Admin(String id, String username, String password, Profile profile) {
        super(id, username, password, Role.ROLE_ADMIN, profile);
    }

    public Admin(String username, String password, Profile profile) {
        super(username, password, Role.ROLE_ADMIN, profile);
    }
}
