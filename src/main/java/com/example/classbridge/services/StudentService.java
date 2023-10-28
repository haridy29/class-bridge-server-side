package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.StudentDto;
import com.example.classbridge.entities.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    ResponseEntity<Student> creatStudent(StudentDto studentDto) throws BadRequestException;
}
