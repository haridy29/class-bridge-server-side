package com.example.classbridge.entities;

import com.example.classbridge.utilities.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student extends User {

    private String studId;
    public Student() {
    }

    public Student(String id, String username, String password,  Profile profile, String studId) {
        super(id, username, password,Role.ROLE_STUDENT, profile);
        this.studId = studId;
    }


    public Student(String username, String password, Profile profile, String studId) {
        super(username, password, Role.ROLE_STUDENT, profile);
        this.studId = studId;
    }


}
