package com.example.demo.controller;

import com.example.demo.Dto.StudentDto;
import com.example.demo.entity.StudentEnt;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testInsert() throws Exception {
        StudentDto dto = new StudentDto(1, "John", "CS", "BLR");
        Mockito.when(studentService.insert(any())).thenReturn("Student Added Successfully");

        mockMvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student Added Successfully"));
    }

    @Test
    void testGetAllDetails() throws Exception {
        Mockito.when(studentService.getAllDetails()).thenReturn(List.of(new StudentEnt()));

        mockMvc.perform(get("/read"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMatchingDetails() throws Exception {
        Mockito.when(studentService.getMatchingDetails(1)).thenReturn(new StudentEnt());

        mockMvc.perform(get("/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateStudent() throws Exception {
        StudentEnt student = new StudentEnt(1, 1, "Jane", "IT", "Chennai");

        Mockito.when(studentService.updateStudent(Mockito.eq(1), any())).thenReturn(student);

        mockMvc.perform(put("/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stdName").value("Jane"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        Mockito.when(studentService.deleteStudent1(1)).thenReturn("Student Deleted Successfully");

        mockMvc.perform(delete("/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student Deleted Successfully"));
    }

    @Test
    void testDeleteAll() throws Exception {
        Mockito.when(studentService.deleteAllStudents()).thenReturn("All students have been deleted successfully.");

        mockMvc.perform(delete("/deleteAll"))
                .andExpect(status().isOk())
                .andExpect(content().string("All students have been deleted successfully."));
    }
}
