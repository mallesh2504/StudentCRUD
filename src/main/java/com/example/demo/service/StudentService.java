package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.StudentDto;
import com.example.demo.entity.StudentEnt;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public String insert(StudentDto request) {
        StudentEnt existing = studentRepo.findByStdRol(request.getStdRol());

        if (existing == null) {
            StudentEnt student = new StudentEnt();
            student.setStdRol(request.getStdRol());
            student.setStdName(request.getStdName());
            student.setSdtDpt(request.getSdtDpt());
            student.setSdtAdd(request.getSdtAdd());

            studentRepo.save(student);
            return "Student Added Successfully";
        } else {
            return "Roll Number Already Exists";
        }
    }

    public List<StudentEnt> getAllDetails() {
        return studentRepo.findAll();
    }

    public StudentEnt getMatchingDetails(Integer stdRol) {
        return studentRepo.findByStdRol(stdRol);
    }

    public StudentEnt updateStudent(Integer stdRol, StudentEnt newStudentData) {
        StudentEnt existingStudent = studentRepo.findByStdRol(stdRol);

        if (existingStudent != null) {
            existingStudent.setStdName(newStudentData.getStdName());
            existingStudent.setSdtDpt(newStudentData.getSdtDpt());
            existingStudent.setSdtAdd(newStudentData.getSdtAdd());

            return studentRepo.save(existingStudent);  
        } else {
            throw new RuntimeException("Student with stdRol " + stdRol + " not found");
        }
    }

    public String deleteStudent1(Integer stdRol) {
        StudentEnt existing = studentRepo.findByStdRol(stdRol);
        if (existing != null) {
            studentRepo.delete(existing); 
            return "Student Deleted Successfully";
        } else {
            return "Student with Roll Number " + stdRol + " not found.";
        }
    }

    public String deleteAllStudents() {
        studentRepo.deleteAll(); 
        return "All students have been deleted successfully.";
    }
}
