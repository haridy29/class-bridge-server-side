package com.example.classbridge.repositories;

import com.example.classbridge.entities.CourseClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseClassRepo extends JpaRepository<CourseClass, String> {
}
