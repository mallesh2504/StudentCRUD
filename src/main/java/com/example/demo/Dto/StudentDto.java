package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Integer stdRol;
    private String stdName;
    private String sdtDpt;
    private String sdtAdd;
}
