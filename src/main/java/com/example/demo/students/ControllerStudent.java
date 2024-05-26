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
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudent (@PathVariable long id){
        if(repo.existsById(id)){
            return repo.findById(id);
        } else {
            return Optional.of(new Student());
        }
    }

    @PatchMapping("/{id}")
    public Student updateStudentName (@PathVariable long id, @RequestBody Student student){
        if(repo.existsById(id)){
            return repo.save(student);
        } else {
            return new Student();
        }
    }

    @PatchMapping("/{id}/activate")
    public Student activateStudent (@PathVariable long id, @RequestParam boolean working){
       return service.activateWork(id, working);
    }
    @DeleteMapping("/{id}")
    public List<Student> deleteStudent(@PathVariable long id){
        repo.deleteById(id);
        return repo.findAll();
    }
}
