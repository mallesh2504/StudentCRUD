package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.Dto.StudentDto;
import com.example.demo.entity.StudentEnt;
import com.example.demo.repository.StudentRepository;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepo;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertNewStudent() {
        StudentDto dto = new StudentDto(1, "John", "CS", "NY");
        when(studentRepo.findByStdRol(1)).thenReturn(null);

        String result = studentService.insert(dto);

        assertEquals("Student Added Successfully", result);
        verify(studentRepo, times(1)).save(any(StudentEnt.class));
    }

    @Test
    void testInsertExistingStudent() {
        StudentDto dto = new StudentDto(1, "John", "CS", "NY");
        when(studentRepo.findByStdRol(1)).thenReturn(new StudentEnt());

        String result = studentService.insert(dto);

        assertEquals("Roll Number Already Exists", result);
    }

    @Test
    void testGetAllDetails() {
        List<StudentEnt> students = Arrays.asList(new StudentEnt(1, 101, "Alice", "Math", "CA"));
        when(studentRepo.findAll()).thenReturn(students);

        List<StudentEnt> result = studentService.getAllDetails();

        assertEquals(1, result.size());
    }

    @Test
    void testGetMatchingDetails() {
        StudentEnt student = new StudentEnt(1, 101, "Bob", "Sci", "TX");
        when(studentRepo.findByStdRol(101)).thenReturn(student);

        StudentEnt result = studentService.getMatchingDetails(101);

        assertEquals("Bob", result.getStdName());
    }

    @Test
    void testUpdateStudent_Success() {
        Integer stdRol = 101;
        StudentEnt existingStudent = new StudentEnt(1, 101, "OldName", "OldDept", "OldAddr");
        StudentEnt newStudentData = new StudentEnt(null, null, "NewName", "NewDept", "NewAddr");

        when(studentRepo.findByStdRol(stdRol)).thenReturn(existingStudent);
        when(studentRepo.save(any(StudentEnt.class))).thenAnswer(invocation -> invocation.getArgument(0));

        StudentEnt updatedStudent = studentService.updateStudent(stdRol, newStudentData);

        assertEquals("NewName", updatedStudent.getStdName());
        assertEquals("NewDept", updatedStudent.getSdtDpt());
        assertEquals("NewAddr", updatedStudent.getSdtAdd());
        verify(studentRepo).save(existingStudent);
    }

    @Test
    void testUpdateStudent_NotFound() {
        Integer stdRol = 999;
        StudentEnt newStudentData = new StudentEnt(null, null, "Test", "Dept", "Addr");

        when(studentRepo.findByStdRol(stdRol)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.updateStudent(stdRol, newStudentData);
        });

        assertEquals("Student with stdRol 999 not found", exception.getMessage());
    }



    @Test
    void testDeleteStudent1_Success() {
        StudentEnt student = new StudentEnt(1, 101, "Eve", "Bio", "FL");
        when(studentRepo.findByStdRol(101)).thenReturn(student);

        String result = studentService.deleteStudent1(101);

        assertEquals("Student Deleted Successfully", result);
        verify(studentRepo, times(1)).delete(student);
    }

    @Test
    void testDeleteStudent1_NotFound() {
        when(studentRepo.findByStdRol(999)).thenReturn(null);

        String result = studentService.deleteStudent1(999);

        assertEquals("Student with Roll Number 999 not found.", result);
        verify(studentRepo, never()).delete(any());
    }

    @Test
    void testDeleteAllStudents() {
        String result = studentService.deleteAllStudents();

        assertEquals("All students have been deleted successfully.", result);
        verify(studentRepo, times(1)).deleteAll();
    }
}
