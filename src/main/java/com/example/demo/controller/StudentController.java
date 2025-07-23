package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Dto.StudentDto;
import com.example.demo.entity.StudentEnt;
import com.example.demo.service.StudentService;

@RestController
//@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public String insert(@RequestBody StudentDto request) {
        return studentService.insert(request);
    }

    @GetMapping("/read")
    public List<StudentEnt> getAllDetails() {
        return studentService.getAllDetails();
    }

    @GetMapping("/get/{stdRol}")
    public StudentEnt getMatchingDetails(@PathVariable Integer stdRol) {
        return studentService.getMatchingDetails(stdRol);
    }

    @PutMapping("/update/{stdRol}")
    public StudentEnt updateStudent(@PathVariable Integer stdRol, @RequestBody StudentEnt student) {
        return studentService.updateStudent(stdRol, student);
    }

    @DeleteMapping("/delete/{stdRol}")
    public String deleteStudent(@PathVariable Integer stdRol) {
        return studentService.deleteStudent1(stdRol);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAll() {
    	return studentService.deleteAllStudents();
    }
}
