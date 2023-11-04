package com.example.classbridge.entities;

import com.example.classbridge.utilities.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "professor")
public class Professor extends User {

    public Professor(String username, String password, Profile profile) {
        super(username, password, Role.ROLE_PROFESSOR, profile);
    }

    public Professor() {
        this.setRole(Role.ROLE_PROFESSOR);
    }

    @OneToMany(mappedBy = "professor",cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<CourseClass> courseClasses;

    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }

    public void setCourseClasses(List<CourseClass> courseClasses) {
        this.courseClasses = courseClasses;
    }
}
