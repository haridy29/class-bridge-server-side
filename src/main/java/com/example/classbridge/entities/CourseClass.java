package com.example.classbridge.entities;

import com.example.classbridge.utilities.Semester;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Year;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "professor_id", "year", "semester"}))
public class CourseClass {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Professor professor;

    private Year year;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    public CourseClass(Course course, Professor professor, Year year, Semester semester) {
        this.course = course;
        this.professor = professor;
        this.year = year;
        this.semester = semester;
    }

    public CourseClass(Course course, Professor professor) {
        this.course = course;
        this.professor = professor;
    }


}
