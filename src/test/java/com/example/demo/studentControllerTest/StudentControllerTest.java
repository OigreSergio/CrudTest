package com.example.demo.studentControllerTest;

import com.example.demo.students.ServiceStudent;
import com.example.demo.students.Student;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ServiceStudent service;
    @Autowired
    private ObjectMapper objectMapper;


    private final Student testStudent = new Student(1L, "Sio", "Worro", false);

    @Test
    public void testCreateStudentCreateAStudent() throws Exception {
        String studentJSON = objectMapper.writeValueAsString(testStudent);

        MvcResult result = this.mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertNotNull(response);
    }

    @Test
    public void testCreateStudentSavesID() throws Exception {
        String studentJSON = objectMapper.writeValueAsString(testStudent);

        MvcResult result = this.mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertEquals(response.getId(), 1L);
    }

    @Test
    public void testCreateStudentSavesName() throws Exception {
        String studentJSON = objectMapper.writeValueAsString(testStudent);

        MvcResult result = this.mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertEquals(response.getName(), "Sio");
    }

    @Test
    public void testCreateStudentSavesSurname() throws Exception {
        String studentJSON = objectMapper.writeValueAsString(testStudent);

        MvcResult result = this.mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertEquals(response.getSurname(), "Worro");
    }

    @Test
    public void testGetAllStudentsReturnsList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/students/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        List<Student> response = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertNotNull(response);
    }

    @Test
    public void testGetAllStudentsReturnsPopulatedList() throws Exception {
        service.createStudent(testStudent);
        MvcResult result = this.mockMvc.perform(get("/students/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        List<Student> response = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertFalse(response.isEmpty());
    }

    @Test
    public void testGetStudentReturns() throws Exception {
        service.createStudent(testStudent);
        MvcResult result = this.mockMvc.perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertNotNull(response);
    }

    @Test
    public void testGetStudentReturnsTheRightStudent() throws Exception {
        service.createStudent(testStudent);
        MvcResult result = this.mockMvc.perform(get("/students/{id}", testStudent.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertEquals(response.getId(), 1L);
    }

    @Test
    public void testUpdateStudentNameReturns() throws Exception {
        service.createStudent(testStudent);
        String studentJSON = objectMapper.writeValueAsString(testStudent);
        MvcResult result = this.mockMvc.perform(patch("/students/{id}", testStudent.getId())
                        .param("name",testStudent.getName())
                        .param("surname",testStudent.getSurname())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertNotNull(response);
    }

    @Test
    public void testUpdateStudentNameChangesName() throws Exception {
        service.createStudent(testStudent);
        String studentJSON = objectMapper.writeValueAsString(testStudent);
        MvcResult result = this.mockMvc.perform(patch("/students/{id}", testStudent.getId())
                        .param("name","Alessio")
                        .param("surname",testStudent.getSurname())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertEquals(response.getName(), "Alessio");
    }

    @Test
    public void testUpdateStudentNameChangesSurname() throws Exception {
        service.createStudent(testStudent);
        String studentJSON = objectMapper.writeValueAsString(testStudent);
        MvcResult result = this.mockMvc.perform(patch("/students/{id}", testStudent.getId())
                        .param("name","Alessio")
                        .param("surname", "Delle Donne")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertEquals(response.getSurname(),"Delle Donne");
    }

    @Test
    public void testActivateStudentReturns() throws Exception {
        service.createStudent(testStudent);
        String studentJSON = objectMapper.writeValueAsString(testStudent);
        MvcResult result = this.mockMvc.perform(patch("/students/{id}/active", testStudent.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertNotNull(response);
    }

    @Test
    public void testActivateStudentSetIsWorkingTrue() throws Exception {
        service.createStudent(testStudent);
        String studentJSON = objectMapper.writeValueAsString(testStudent);
        MvcResult result = this.mockMvc.perform(patch("/students/{id}/active", testStudent.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Student response = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertTrue(response.isWorking());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        service.createStudent(testStudent);
        MvcResult result = this.mockMvc.perform((delete("/students/{id}", testStudent.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        List<Student> response = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertTrue(response.isEmpty());
    }

    @Test
    public void testDeleteStudentRemovesASpecificStudent() throws Exception {
        service.createStudent(testStudent);
        Student otherStudent = new Student(2,"Alessio", "Delle Donne", true);
        service.createStudent(otherStudent);
        MvcResult result = this.mockMvc.perform((delete("/students/{id}", testStudent.getId())))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        List<Student> response = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        assertFalse(response.contains(testStudent));
    }
}
