package com.example.fulldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
One teacher can teach many courses
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;

    @Column(
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            columnDefinition = "TEXT"
    )
    private String lastName;

    /*
    @OneToMany(                                  // First defined 1 teacher -> many courses. Preference is ManyToOne relationship
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )

    private List<Course> courses;
     */
}
