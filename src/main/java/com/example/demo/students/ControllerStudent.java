package com.example.demo.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class ControllerStudent {

    @Autowired
    private ServiceStudent service;

    @Autowired
    private RepositoryStudent repo;

    @PostMapping
    public Student createStudent (@RequestBody Student student){
        return service.createStudent(student);
    }

    @GetMapping("/all")
    public List<Student> findAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent (@PathVariable long id){
        return service.getStudentById(id);
    }

    @PatchMapping("/{id}")
    public Student updateStudentName (@PathVariable long id, @RequestParam String name, @RequestParam String surname){
        return service.updateStudentGeneralInfo(id, name, surname);
    }

    @PatchMapping("/{id}/active")
    public Student activateStudent (@PathVariable long id){
        return service.activateWork(id);
    }
    @DeleteMapping("/{id}")
    public List<Student> deleteStudent(@PathVariable long id){
        return service.deleteAStudent(id);
    }
}
