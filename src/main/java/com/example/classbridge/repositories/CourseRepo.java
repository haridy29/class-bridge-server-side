package com.example.classbridge.repositories;

import com.example.classbridge.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, String> {
    boolean existsByNameIgnoreCase(String name);
     <T> T findByName(String name);
}
