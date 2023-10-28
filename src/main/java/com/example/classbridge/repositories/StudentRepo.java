package com.example.classbridge.repositories;

import com.example.classbridge.entities.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends AbstractRepo<Student> {
    boolean existsByStudId(String studId);



}
