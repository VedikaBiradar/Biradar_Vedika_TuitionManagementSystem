package com.vedikabiradar.biradar_vedika_tuitionmanagementsystem_casestudy.Entity;



import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Student {

    //properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String studentName;
    private LocalDate dob;
    private String bloodGroup;
    private int daysPerWeek;
    private String gender;
    private String standard;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private  User user;

    // Getters and Setters

    public int getAge() {
        return LocalDate.now().getYear() - dob.getYear();
    }
}
