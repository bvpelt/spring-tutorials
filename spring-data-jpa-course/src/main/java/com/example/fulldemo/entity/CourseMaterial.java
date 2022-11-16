package com.example.fulldemo.entity;

import lombok.*;

import javax.persistence.*;

/*
Coursematerial can only be used for one course
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "course")                     // Needed for fetchtype.lazy on course
@Table (
        name = "course_material",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "coursematerial_course_id_unique",
                        columnNames = "course_id"
                )
        }
)
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;

    @Column(
            columnDefinition = "TEXT"
    )
    private String url;

    @OneToOne(
            cascade = CascadeType.ALL,            // Prevent transient course objects
            fetch = FetchType.LAZY,               // Do not fetch related courses unless explicit instructed
            optional = false                      // By default optional is true meaning, a course can not exist without coursematerial
    )
    @JoinColumn(                                  // Relation to course first defined as uni-directional coursematerial -> course
            name = "course_id",                   // Add course_id to table course_material
            referencedColumnName = "courseId",   // Use course.courseId in this field
            foreignKey = @ForeignKey(
                    name = "coursematerial_course_id_fk"
            )
    )
    private Course course;
}
