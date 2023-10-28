package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.ProfileDto;
import com.example.classbridge.dtos.StudentDto;
import com.example.classbridge.dtos.UserDto;
import com.example.classbridge.entities.Profile;
import com.example.classbridge.entities.Student;
import com.example.classbridge.repositories.StudentRepo;
import com.example.classbridge.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    void canCreate(StudentDto studentDto) throws BadRequestException {
        UserDto userDto = studentDto.getUserDto();
        ProfileDto profileDto = userDto.getProfileDto();
        if (studentRepo.existsByStudId(studentDto.getStudId())) {
            throw new BadRequestException("The student ID already token");
        } else if (userRepo.existsByUsernameIgnoreCase(userDto.getUsername())) {
            throw new BadRequestException("The username already token");
        } else if (userRepo.existsByProfileEmail(profileDto.getEmail())) {
            throw new BadRequestException("The Email already registered");
        } else if (userRepo.existsByProfilePhone(profileDto.getPhone())) {
            throw new BadRequestException("The Phone number already registered");
        }
    }

    @Override
    public ResponseEntity<Student> creatStudent(StudentDto studentDto) throws BadRequestException {
        canCreate(studentDto);
        UserDto userDto = studentDto.getUserDto();
        ProfileDto profileDto = userDto.getProfileDto();
        Profile newProfile = new Profile(profileDto.getEmail(), profileDto.getFirstName(), profileDto.getLastName(), profileDto.getPhone(), profileDto.getGender(), profileDto.getDateOfBirth());
        Student newStudent = new Student(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), newProfile, studentDto.getStudId());
        try {
            newStudent = studentRepo.save(newStudent);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return ResponseEntity.ok(newStudent);
    }
}
