package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseClassDto;
import com.example.classbridge.entities.CourseClass;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseClassService {
    ResponseEntity<CourseClass> createCourseClass(CourseClassDto courseClassDto) throws BadRequestException;

}
