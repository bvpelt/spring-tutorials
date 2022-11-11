package com.example.fulldemo.repository;

import com.example.fulldemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String LastName);

    Student findByEmailId(String emailid);

    /*
    JPQL: Query based on java object not table column names!!!
     */
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);
}
