package com.example.fulldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
A course has only one type coursematerial
A course only has 1 teacher
A couse has many students
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;

    @Column(
            columnDefinition = "TEXT"
    )
    private String title;
    private Integer credit;

    @OneToOne(                             // Implementating bi-directoral relation
            mappedBy = "course"          // Using course from coursematerial for the relation
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId",
            foreignKey = @ForeignKey(
                    name = "course_teacher_id_fk"
            )
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "student_course_map",                  // A new database table is needed to have course_id and student_id columns
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId",
                    foreignKey = @ForeignKey(
                            name = "student_course_course_id_fk"
                    )
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId",
                    foreignKey = @ForeignKey(
                            name = "student_course_student_id_fk"
                    )
            )
    )
    private List<Student> students;

    public void addStudents(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
