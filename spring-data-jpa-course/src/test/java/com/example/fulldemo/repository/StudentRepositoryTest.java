package com.example.fulldemo.repository;

import com.example.fulldemo.Application;
import com.example.fulldemo.entity.Guardian;
import com.example.fulldemo.entity.Student;
import com.example.fulldemo.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;


@SpringBootTest(classes = Application.class)
//@DataJpaTest()
@Slf4j
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent() {
        log.info("Start test: saveStudent");
        Student student = studentRepository.findByEmailId("xx@gmail.com");

        if (student == null) {
            student = Student.builder()
                    .emailId("xx@gmail.com")
                    .firstName("xx")
                    .lastName("xxxx")
                    // .guardianName("zzzz")
                    // .guardianEmail("zzzz@gmail.com")
                    // .guardianMobile("999933333")
                    .build();

        Student savedStudent = studentRepository.save(student);
        Assert.notNull(savedStudent, "Student not saved");
        }
        log.info("End   test: saveStudent");
    }

    @Test
    public void printAllStudents() {
        log.info("Start test: printAllStudents");
        Student student = studentRepository.findByEmailId("xy@gmail.com");

        if (student == null) {
            student = Student.builder()
                    .emailId("xy@gmail.com")
                    .firstName("xx")
                    .lastName("xxxx")
                    // .guardianName("zzzz")
                    //  .guardianEmail("zzzz@gmail.com")
                    //  .guardianMobile("999933333")
                    .build();

            studentRepository.save(student);
        }

        List<Student> studentList = studentRepository.findAll();
        Assert.isTrue(studentList.size() > 0, "No students found at least one expected");

        studentList.forEach(currentStudent -> {
            log.info("Student: {}", currentStudent);
        });
        log.info("End   test: printAllStudents");
    }

    @Test
    public void saveStudentWithGuardian() {
        log.info("Start test: saveStudentWithGuardian");
        Guardian guardian = Guardian.builder()
                .email("zzzz@gmail.com")
                .name("zzzz")
                .mobile("999933333")
                .build();

        Student student = studentRepository.findByEmailId("xxxx@gmail.com");
        if (student == null) {
            student = Student.builder()
                    .firstName("xxxx")
                    .emailId("xxxx@gmail.com")
                    .lastName("zzzz")
                    .guardian(guardian)
                    .build();

            studentRepository.save(student);
        }
        List<Student> studentList = studentRepository.findAll();
        Assert.isTrue(studentList.size() > 0, "No students found at least one expected");

        studentList.forEach(currentStudent -> {
            log.info("Student: {}", currentStudent);
        });
        log.info("End   test: saveStudentWithGuardian");
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
        if (email == null) {
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
        if (email == null) {
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
        if (email == null) {
            studentRepository.save(student);
        }

    }


    @Test
    public void printStudentByFirstName() {
        log.info("Start test: printStudentByFirstName");
        fillData();

        log.info("Search student with firstname aaaa - expect not found");
        List<Student> studentList = studentRepository.findByFirstName("aaaa");
        Assert.isTrue(studentList.size() == 0, "Students found, none expected");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }

        log.info("Search student with firstname bbbb - expect found");
        studentList = studentRepository.findByFirstName("bbbb");
        Assert.isTrue(studentList.size() > 0, "No students found at least one expected");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }
        log.info("End   test: printStudentByFirstName");
    }

    @Test
    public void printStudentByFirstNameContaining() {
        log.info("Start test: printStudentByFirstNameContaining");
        fillData();

        log.info("Search student with firstname containing cc - expect not found");
        List<Student> studentList = studentRepository.findByFirstNameContaining("cc");
        Assert.isTrue(studentList.size() == 0, "Students found, none expected");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }

        log.info("Search student with firstname containing dd - expect found");
        studentList = studentRepository.findByFirstNameContaining("dd");
        Assert.isTrue(studentList.size() > 0, "No students found at least one expected");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }
        log.info("End   test: printStudentByFirstNameContaining");    }

    @Test
    public void printStudentBasedOnGuardianName() {
        log.info("Start test: printStudentBasedOnGuardianName");
        fillData();

        log.info("Search student with guardianname bbbb - expect not found");
        List<Student> studentList = studentRepository.findByGuardianName("bbbb");
        Assert.isTrue(studentList.size() == 0, "Students found, none expected");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }

        log.info("Search student with guardianname aaaa - expect found");
        studentList = studentRepository.findByGuardianName("aaaa");
        Assert.isTrue(studentList.size() > 0, "No students found, at least one expected");

        if (studentList.isEmpty()) {
            log.info("No students found");
        } else {
            studentList.forEach(currentStudent -> {
                log.info("Student: {}", currentStudent);
            });
        }
        log.info("End   test: printStudentBasedOnGuardianName");
    }

    @Test
    public void printGetStudentByEmailAddress() {
        log.info("Start test: printGetStudentByEmailAddress");
        fillData();

        log.info("Search student with emailid xxx2@gmail.com - expect not found");
        Student student = studentRepository.getStudentByEmailAddress("xxx2@gmail.com");
        Assert.isNull(student, "Student found, none expected");

        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }

        log.info("Search student with emailid xxx1@gmail.com - expect  found");
        student = studentRepository.getStudentByEmailAddress("xxx1@gmail.com");
        Assert.notNull(student, "Student not found, at leas one expected");

        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }
        log.info("End   test: printGetStudentByEmailAddress");
    }

    @Test
    public void printGetStudentFirstNameByEmailAddress() {
        log.info("Start test: printGetStudentFirstNameByEmailAddress");
        fillData();

        log.info("Search firstName with emailid xxx2@gmail.com - expect not found");
        String firstName = studentRepository.getStudentFirstNameByEmailAddress("xxx2@gmail.com");
        Assert.isNull(firstName, "Student found, none expected");

        if (firstName == null) {
            log.info("No students found");
        } else {
            log.info("firstName: {}", firstName);
        }

        log.info("Search student with guardianname xxx1@gmail.com - expect  found");
        firstName = studentRepository.getStudentFirstNameByEmailAddress("xxx1@gmail.com");
        Assert.notNull(firstName, "Student not found, at leas one expected");

        if (firstName == null) {
            log.info("No firstName found");
        } else {
            log.info("firstName: {}", firstName);
        }
        log.info("End   test: printGetStudentFirstNameByEmailAddress");
    }

    @Test
    public void printGetStudentByEmailAddressNative() {
        log.info("Start test: printGetStudentByEmailAddressNative");
        fillData();

        log.info("Search student with emailid xxx2@gmail.com - expect not found");
        Student student = studentRepository.getStudentByEmailAddressNative("xxx2@gmail.com");
        Assert.isNull(student, "Student found, none expected");

        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }

        log.info("Search student with emailid xxx1@gmail.com - expect  found");
        student = studentRepository.getStudentByEmailAddressNative("xxx1@gmail.com");
        Assert.notNull(student, "Student not found, at leas one expected");

        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }
        log.info("End   test: printGetStudentByEmailAddressNative");
    }

    @Test
    public void printGetStudentByEmailAddressNativeNamedParam() {
        log.info("Start test: printGetStudentByEmailAddressNativeNamedParam");
        fillData();

        log.info("Search student with emailid xxx2@gmail.com - expect not found");
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("xxx2@gmail.com");
        Assert.isNull(student, "Student found, none expected");

        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }

        log.info("Search student with emailid xxx1@gmail.com - expect  found");
        student = studentRepository.getStudentByEmailAddressNativeNamedParam("xxx1@gmail.com");
        Assert.notNull(student, "Student not found, at leas one expected");

        if (student == null) {
            log.info("No students found");
        } else {
            log.info("Student: {}", student);
        }
        log.info("End   test: printGetStudentByEmailAddressNativeNamedParam");
    }

    @Test
    public void updateStudentNameByEmailId() {
        log.info("Start test: updateStudentNameByEmailId");
        fillData();

        String emailId = "xxx1@gmail.com";
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam(emailId);
        log.info("firstname of student with email {} is {}", emailId, student.getFirstName());
        studentRepository.updateStudentNameByEmailId("newname", emailId);
        Student newstudent = studentRepository.getStudentByEmailAddressNativeNamedParam(emailId);
        log.info("firstname of student with email {} is {}", emailId, newstudent.getFirstName());
        log.info("End   test: updateStudentNameByEmailId");
    }
}