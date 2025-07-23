package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentEnt;

@Repository
public interface StudentRepository extends JpaRepository<StudentEnt, Integer> {
    public StudentEnt findByStdRol(Integer stdRol);
    public void deleteByStdRol(Integer stdRol);  
}
