package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseDto;
import com.example.classbridge.entities.Course;
import com.example.classbridge.repositories.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;

    private boolean isValidDto(CourseDto course) {

        if (courseRepo.existsByNameIgnoreCase(course.getCourseName())) return false;

        if (course.getPrerequisiteNames() == null || course.getPrerequisiteNames().isEmpty())
            return true;

        for (String courseName : course.getPrerequisiteNames()) {
            if (!courseRepo.existsByNameIgnoreCase(courseName)) {
                return false;
            }
        }

        return true;
    }

    private String getErrorMessage(CourseDto course) {
        if (courseRepo.existsByNameIgnoreCase(course.getCourseName())) return "The course name already exists";
        for (String courseName : course.getPrerequisiteNames()) {
            if (!courseRepo.existsByNameIgnoreCase(courseName)) {
                {
                    return "The " + courseName + " Not exists";
                }
            }
        }
        return "";
    }

    @Override
    public Course addCourse(CourseDto courseDto) throws BadRequestException {

        if (!isValidDto(courseDto)) {
            throw new BadRequestException(getErrorMessage(courseDto));
        }

        Course newCourse = new Course();
        newCourse.setName(courseDto.getCourseName());

        if (courseDto.getPrerequisiteNames() != null) {
            for (String courseName : courseDto.getPrerequisiteNames()) {
                newCourse.getPrerequisites().add(courseRepo.findByName(courseName));
            }
        }

        return courseRepo.save(newCourse);
    }
}
