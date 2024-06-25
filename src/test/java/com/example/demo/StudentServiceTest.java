package com.example.demo;

import com.example.demo.students.RepositoryStudent;
import com.example.demo.students.ServiceStudent;
import com.example.demo.students.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class StudentServiceTest {

    @Mock
    private RepositoryStudent repo;

    @InjectMocks
    private ServiceStudent service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateAStudent() {
        Student testStudent = new Student();
        testStudent.setName("Sio");
        testStudent.setSurname("Sergio");
        testStudent.setWorking(true);

        Student inserted = new Student();
        inserted.setId(1);
        inserted.setName("Sio");
        inserted.setSurname("Sergio");
        inserted.setWorking(true);

        when(repo.save(testStudent))
                .thenReturn(inserted);

        Student result = service.createStudent(testStudent);
        assertEquals(result, inserted);
    }

    @Test
    public void testGetAllStudents() {
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Sio");
        student1.setSurname("Sergio");
        student1.setWorking(true);

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Sergio");
        student2.setSurname("Hanganu");
        student2.setWorking(true);

        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        when(repo.findAll())
                .thenReturn(students);
        Collection<Student> result = service.getAllStudents();

        assertEquals(result, students);
    }

    @Test
    public void testActivateWork() {
        Student student = new Student();
        student.setId(1);
        student.setName("Sio");
        student.setSurname("Sergiio");
        student.setWorking(false);

        Student inserted = new Student();
        inserted.setId(1L);
        inserted.setName("Sio");
        inserted.setSurname("Sergio");
        inserted.setWorking(true);
        when(repo.existsById(1l))
                .thenReturn(true);
        when(repo.findById(1L))
                .thenReturn(Optional.of(student));
        when(repo.save(Optional.of(student).get()))
                .thenReturn(inserted);
        Student result = service.activateWork(1);
        assertEquals(result, inserted);
    }

    @Test
    public void testGetStudentById() {
        Student student = new Student();
        student.setId(1);
        student.setName("Sio");
        student.setSurname("Sergio");
        student.setWorking(false);

        when(repo.existsById(1L))
                .thenReturn(true);
        when(repo.findById(1L))
                .thenReturn(Optional.of(student));

        Student result = service.getStudentById(1L);
        assertEquals(result, student);
    }

    @Test
    public void testUpdateStudentGeneralInfo() {
        Student student = new Student();
        student.setId(1);
        student.setName("Sio");
        student.setSurname("Sergio");
        student.setWorking(true);


        when(repo.existsById(1L))
                .thenReturn(true);
        when(repo.findById(1L))
                .thenReturn(Optional.of(student));
        Student result = service.updateStudentGeneralInfo(1L, "Alessio", "Delle Donne");
        assertEquals(result, student);
    }
}
