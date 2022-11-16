package com.example.fulldemo.repository;

import com.example.fulldemo.Application;
import com.example.fulldemo.entity.Course;
import com.example.fulldemo.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@Slf4j
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    private void fillTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .teacher(teacher)
                .build();
        Course courseJAVA = Course.builder()
                .title("Java")
                .credit(8)
                .teacher(teacher)
                .build();

        teacherRepository.save(teacher);
    }

    private void fillCourse() {
        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(5)
                .teacher(teacher)
                .build();

        courseRepository.save(courseDBA);
    }

    @Test
    public void saveTeacher() {
        log.info("Start test: saveTeacher");
        fillTeacher();

        List<Teacher> teachers = teacherRepository.findAll();
        Assert.isTrue(teachers.size() > 0, "No teachers found, teachers expected");

        teachers.forEach(teacher -> {
            log.info("Teacher: {}", teacher);
        });
        log.info("End   test: saveTeacher");
    }


    @Test
    public void saveCourseWithTeacher() {
        log.info("Start test: saveCourseWithTeacher");
        fillCourse();

        List<Course> courses = courseRepository.findAll();
        Assert.isTrue(courses.size() > 0, "No courses found, courses expected");

        courses.forEach(course -> {
            log.info("Course: {}", course);
        });
        log.info("End   test: saveCourseWithTeacher");
    }
}