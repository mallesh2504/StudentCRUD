package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stdId;

    private Integer stdRol;
    private String stdName;
    private String sdtDpt;
    private String sdtAdd;
}
