package com.example.fulldemo.repository;

import com.example.fulldemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    /*
    Native queries, based on database table
     */

    @Query(
            value = "SELECT * from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


    @Query(
            value = "SELECT * from tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    /*
Update data
 */
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
