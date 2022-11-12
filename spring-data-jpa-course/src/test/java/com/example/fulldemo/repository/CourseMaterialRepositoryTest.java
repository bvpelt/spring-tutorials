package com.example.fulldemo.repository;

import com.example.fulldemo.Application;
import com.example.fulldemo.entity.Course;
import com.example.fulldemo.entity.CourseMaterial;
import com.example.fulldemo.repository.CourseMaterialRepository;
import com.example.fulldemo.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

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

    public void showAllCourseMaterial(List<CourseMaterial> courseMaterialList) {

        courseMaterialList.forEach(courseMaterial -> {
            log.info("Found: {}", courseMaterial);
        });
    }

    @Test
    public void SaveCourseMaterial() {

        fillCourseMaterial();

        List<CourseMaterial> courseMaterialList = repository.findAll();
        Assert.isTrue(courseMaterialList.size() > 0, "No coursematerial found");
        showAllCourseMaterial(courseMaterialList);
    }
}
