package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseDto;
import com.example.classbridge.entities.Course;

public interface CourseService {
    Course addCourse(CourseDto courseDto) throws BadRequestException;


}
