package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseClassDto;
import com.example.classbridge.entities.Course;
import com.example.classbridge.entities.CourseClass;
import com.example.classbridge.entities.Professor;
import com.example.classbridge.repositories.CourseClassRepo;
import com.example.classbridge.repositories.CourseRepo;
import com.example.classbridge.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseClassServiceImpl implements CourseClassService {
    private final CourseClassRepo courseClassRepo;
    private final ProfessorRepository professorRepository;
    private final CourseRepo courseRepo;


    void validateDto(CourseClassDto courseClassDto) throws BadRequestException {
        if (!courseRepo.existsByNameIgnoreCase(courseClassDto.getCourseName())) {
            throw new BadRequestException("Invalid Course name");
        }
        if (!professorRepository.existsByUsernameIgnoreCase(courseClassDto.getProfessorName())) {
            throw new BadRequestException("Invalid Professor name");

        }

    }

    @Override
    public ResponseEntity<CourseClass> createCourseClass(CourseClassDto courseClassDto) throws BadRequestException {
        validateDto(courseClassDto);
        Course course = courseRepo.findByNameIgnoreCase(courseClassDto.getCourseName());
        Professor professor = professorRepository.findByUsernameIgnoreCase(courseClassDto.getProfessorName());
        CourseClass courseClass = new CourseClass(course, professor, Year.of(courseClassDto.getYear()), courseClassDto.getSemester());
        try {

            courseClass = courseClassRepo.save(courseClass);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }

        return ResponseEntity.ok(courseClass);
    }


}
