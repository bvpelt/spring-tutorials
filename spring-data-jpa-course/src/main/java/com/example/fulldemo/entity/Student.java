package com.example.fulldemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
A student follows many courses
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long studentId;

    @Column(
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "email_address",
            columnDefinition = "TEXT",
            nullable = false)
    private String emailId;

    @Embedded
    private Guardian guardian;


}
