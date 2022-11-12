package com.example.fulldemo.repositorytest;

import com.example.fulldemo.Application;
import com.example.fulldemo.entity.Course;
import com.example.fulldemo.entity.CourseMaterial;
import com.example.fulldemo.repository.CourseMaterialRepository;
import com.example.fulldemo.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
@Slf4j
public class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    private void fillCourseMaterial() {

        Course course = Course.builder()
                .title("DSA")
                .credit(6)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }

    @Test
    public void SaveCourseMaterial() {

        fillCourseMaterial();
    }
}
