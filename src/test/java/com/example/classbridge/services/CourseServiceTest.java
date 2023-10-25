package com.example.classbridge.services;

import com.example.classbridge.config.exception.BadRequestException;
import com.example.classbridge.dtos.CourseDto;
import com.example.classbridge.entities.Course;
import com.example.classbridge.repositories.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@RequiredArgsConstructor
class CourseServiceTest {
    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepo courseRepo;
    private CourseDto courseDto;

    @BeforeEach
    void setUp() {
        courseDto = new CourseDto();
        courseDto.setCourseName("Operating Systems");
        courseDto.setPrerequisiteNames(Arrays.asList("Networks"));
        when(courseRepo.existsByNameIgnoreCase("Operating Systems")).thenReturn(false);
    }

    @Test
    public void addNewCourse_theCourseNameAlreadyExists_ExceptionThrown() {
        String courseName = "Operating Systems";
        when(courseRepo.existsByNameIgnoreCase(courseName)).thenReturn(true);
        Exception ex = assertThrows(BadRequestException.class, () -> {
            courseService.addCourse(courseDto);
        });
        String errorMessage = "The course name already exists";
        assertEquals(ex.getMessage(), errorMessage);
    }

    @Test
    public void addNewCourse_theCoursePrerequisitesNotExist_ExceptionThrown() {
        String prerequisiteName = "Networks";
        when(courseRepo.existsByNameIgnoreCase(prerequisiteName)).thenReturn(false);
        Exception ex = assertThrows(BadRequestException.class, () -> {
            courseService.addCourse(courseDto);
        });
        String errorMessage = "The " + prerequisiteName + " Not exists";
        assertEquals(ex.getMessage(), errorMessage);
    }


    @Test
    public void addNewCourse_GivenCourseDtoWithValidCourseNameAndNullPrerequisites_WillReturnCourse() {
        courseDto.setPrerequisiteNames(null);
        String courseName = "Operating Systems";
        Course course = createCourse(courseName, new ArrayList<>());

        when(courseRepo.save(course)).thenReturn(course);
        try {

            Course createdCourse = courseService.addCourse(courseDto);
            assertEquals(createdCourse, course);
            assertEquals(createdCourse.getName(), courseDto.getCourseName());
            assertNotNull(createdCourse);
        } catch (Exception ex) {

            fail(ex.getMessage());
        }

    }

    private Course createCourse(String courseName, List<String> prerequisites) {
        Course course = new Course();
        course.setName(courseName);

        for (String preName : prerequisites) {
            course.getPrerequisites().add(new Course(preName));
        }
        return course;
    }

    @Test
    public void addNewCourse_GivenCourseDtoWithValidCourseNameAndValidPrerequisites_WillReturnCourse() {

        courseDto.setPrerequisiteNames(Arrays.asList("Networks","Data Structures"));

        Course course = createCourse(courseDto.getCourseName(), courseDto.getPrerequisiteNames());
        when(courseRepo.existsByNameIgnoreCase("Data Structures")).thenReturn(true);
        when(courseRepo.existsByNameIgnoreCase("Networks")).thenReturn(true);
        when(courseRepo.save(course)).thenReturn(course);
        when(courseRepo.findByName("Networks")).thenReturn(course.getPrerequisites().get(0));
        when(courseRepo.findByName("Data Structures")).thenReturn(course.getPrerequisites().get(1));

        try {
            Course createdCourse = courseService.addCourse(courseDto);
            assertEquals(createdCourse, course);
            assertEquals(createdCourse.getName(), courseDto.getCourseName());

        } catch (Exception ex) {
            fail(ex.getMessage());
        }


    }

}