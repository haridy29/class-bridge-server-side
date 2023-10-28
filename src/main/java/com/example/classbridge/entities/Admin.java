package com.example.classbridge.entities;


import com.example.classbridge.utilities.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {
    public Admin() {

    }

    public Admin(String id, String username, String password, Profile profile) {
        super(id, username, password, Role.ROLE_ADMIN, profile);
    }

    public Admin(String username, String password, Profile profile) {
        super(username, password, Role.ROLE_ADMIN, profile);
    }
}
