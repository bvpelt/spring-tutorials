package com.example.fulldemo.repository;

import com.example.fulldemo.Application;
import com.example.fulldemo.entity.Course;
import com.example.fulldemo.entity.CourseMaterial;
import com.example.fulldemo.entity.Student;
import com.example.fulldemo.entity.Teacher;
import com.example.fulldemo.repository.CourseMaterialRepository;
import com.example.fulldemo.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = Application.class)
@Slf4j
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Autowired
    private StudentRepository studentRepository;

    private void fillCourses() {
        Course course = Course.builder()
                .credit(6)
                .title("DSA")
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.google.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printCourses() {
        fillCourses();

        List<Course> courses = courseRepository.findAll();
        Assert.isTrue(courses.size() > 0, "No courses found - courses expected");

        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });
    }


    @Test
    public void findAllPagination() {
        Pageable firstPagewithThreeRecords = PageRequest.of(0, 3);
        Pageable secondPagewithTwoRecords = PageRequest.of(1,2);

        List<Course> courses =  courseRepository.findAll(firstPagewithThreeRecords).getContent();
        long totalElements = courseRepository.findAll(firstPagewithThreeRecords).getTotalElements();
        long totalPages = courseRepository.findAll(firstPagewithThreeRecords).getTotalPages();

        log.info("Total elements: {} - Total pages: {}", totalElements, totalPages);
        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });

        courses =  courseRepository.findAll(secondPagewithTwoRecords).getContent();
        totalElements = courseRepository.findAll(secondPagewithTwoRecords).getTotalElements();
        totalPages = courseRepository.findAll(secondPagewithTwoRecords).getTotalPages();

        log.info("Total elements: {} - Total pages: {}", totalElements, totalPages);
        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });
    }


    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                5,
                Sort.by("title"));
        Pageable sortByCreditDesc = PageRequest.of(
                0,
                5,
                Sort.by("credit").descending());
        Pageable sortByTitleAndCreditDesc = PageRequest.of(
                0,
                5,
                Sort.by("title").descending()
                        .and(Sort.by("credit")));

        List<Course> courses =  courseRepository.findAll(sortByTitle).getContent();
        long totalElements = courseRepository.findAll(sortByTitle).getTotalElements();
        long totalPages = courseRepository.findAll(sortByTitle).getTotalPages();

        log.info("Total elements: {} - Total pages: {}", totalElements, totalPages);
        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });

        courses =  courseRepository.findAll(sortByCreditDesc).getContent();
        totalElements = courseRepository.findAll(sortByCreditDesc).getTotalElements();
        totalPages = courseRepository.findAll(sortByCreditDesc).getTotalPages();

        log.info("Total elements: {} - Total pages: {}", totalElements, totalPages);
        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });

        courses =  courseRepository.findAll(sortByTitleAndCreditDesc).getContent();
        totalElements = courseRepository.findAll(sortByTitleAndCreditDesc).getTotalElements();
        totalPages = courseRepository.findAll(sortByTitleAndCreditDesc).getTotalPages();

        log.info("Total elements: {} - Total pages: {}", totalElements, totalPages);
        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });
    }

    @Test
    public void printfindByTitleContaining() {
        Pageable firstPageTenRecoreds = PageRequest.of(0,10);

        List<Course> courses = courseRepository.findByTitleContaining("D",
                firstPageTenRecoreds).getContent();

        courses.forEach(course -> {
            log.info("Found course: {}", course);
        });
    }


    @Test
    @Transactional
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Lizzy")
                .lastName("Morgan")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        Student student = studentRepository.findByEmailId("nico.voogd@gmail.com");

        if (student == null) {
            student = Student.builder()
                    .firstName("Nico")
                    .lastName("Voogd")
                    .emailId("nico.voogd@gmail.com")
                    .build();
        }
        course.addStudents(student);

        courseRepository.save(course);
    }
}
