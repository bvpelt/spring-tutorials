package com.example.fulldemo.repositorytest;

import com.example.fulldemo.Application;
import com.example.fulldemo.entity.Guardian;
import com.example.fulldemo.entity.Student;
import com.example.fulldemo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest(classes = Application.class )
//@DataJpaTest()
@Slf4j
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        Student student = Student.builder()
                .emailId("xx@gmail.com")
                .firstName("xx")
                .lastName("xxxx")
               // .guardianName("zzzz")
               // .guardianEmail("zzzz@gmail.com")
               // .guardianMobile("999933333")
                .build();

        studentRepository.save(student);

    }

    @Test
    public void printAllStudents() {
        Student student = Student.builder()
                .emailId("xy@gmail.com")
                .firstName("xx")
                .lastName("xxxx")
               // .guardianName("zzzz")
              //  .guardianEmail("zzzz@gmail.com")
              //  .guardianMobile("999933333")
                .build();

        studentRepository.save(student);

        List<Student> studentList = studentRepository.findAll();

        studentList.forEach(currentStudent -> {
            log.info("Student: {}", currentStudent);
        });
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .email("zzzz@gmail.com")
                .name("zzzz")
                .mobile("999933333")
                .build();

        Student student = Student.builder()
                .firstName("xxxx")
                .emailId("xxxx@gmail.com")
                .lastName("zzzz")
                .guardian(guardian)
                .build();

        studentRepository.save(student);

        List<Student> studentList = studentRepository.findAll();

        studentList.forEach(currentStudent -> {
            log.info("Student: {}", currentStudent);
        });
    }

    private void fillData() {
        Guardian guardian = Guardian.builder()
                .email("zzzz@gmail.com")
                .name("zzzz")
                .mobile("999933333")
                .build();

        Student student = Student.builder()
                .firstName("xxxx")
                .emailId("xxx1@gmail.com")
                .lastName("zzzz")
                .guardian(guardian)
                .build();

        Student email = studentRepository.findByEmailId("xxx1@gmail.com");
        if (email != null) {
            studentRepository.save(student);
        }

        guardian = Guardian.builder()
                .email("aaaa@gmail.com")
                .name("aaaa")
                .mobile("888833333")
                .build();

        student = Student.builder()
                .firstName("bbbb")
                .emailId("bbbb@gmail.com")
                .lastName("yyyy")
                .guardian(guardian)
                .build();

        email = studentRepository.findByEmailId("bbbb@gmail.com");
        if (email != null) {
            studentRepository.save(student);
        }

        guardian = Guardian.builder()
                .email("acca@gmail.com")
                .name("acca")
                .mobile("888833333")
                .build();

        student = Student.builder()
                .firstName("bddb")
                .emailId("bddb@gmail.com")
                .lastName("yxxy")
                .guardian(guardian)
                .build();

        email = studentRepository.findByEmailId("bddb@gmail.com");
        if (email != null) {
            studentRepository.save(student);
        }

    }


    @Test
    public void printStudentByFirstName() {

        fillData();

        log.info("Search student with firstname aaaa - expect not found");
        List<Student> studentList = studentRepository.findByFirstName("aaaa");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }

        log.info("Search student with firstname bbbb - expect found");
        studentList = studentRepository.findByFirstName("bbbb");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }
    }

    @Test
    public void printStudentByFirstNameContaining() {

        fillData();

        log.info("Search student with firstname containing cc - expect not found");
        List<Student> studentList = studentRepository.findByFirstNameContaining("cc");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }

        log.info("Search student with firstname containing dd - expect found");
        studentList = studentRepository.findByFirstNameContaining("dd");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }
    }

    @Test
    public void printStudentBasedOnGuardianName() {

        fillData();

        log.info("Search student with guardianname bbbb - expect not found");
        List<Student> studentList = studentRepository.findByGuardianName("bbbb");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }

        log.info("Search student with guardianname aaaa - expect found");
        studentList = studentRepository.findByGuardianName("aaaa");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }
    }

    @Test
    public void printGetStudentByEmailAddress() {

        fillData();

        List<Student> studentList = studentRepository.findAll();

        studentList.forEach(currentStudent -> {
            log.info("Student: {}", currentStudent);
        });

        log.info("Search student with guardianname xxx2@gmail.com - expect not found");
        Student student = studentRepository.getStudentByEmailAddress("xxx2@gmail.com");

        if (student == null) {
            log.info("No students found");
        } else {
                log.info("Student: {}", student);
        }

        log.info("Search student with guardianname xxx1@gmail.com - expect  found");
        student = studentRepository.getStudentByEmailAddress("xxx1@gmail.com");


        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }

    }
}