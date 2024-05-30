package com.example.demo.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceStudent {

    @Autowired
    private RepositoryStudent repo;

    public Student activateWork(long id) {
        if (repo.existsById(id)) {
            Student activeStudent = repo.findById(id).get();
            activeStudent.setWorking(true);
            activeStudent.setId(id);
            return repo.save(activeStudent);
        } else {
            return new Student();
        }
    }

    public Student createStudent(Student student) {
        return repo.save(student);
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(long id) {
        if (repo.existsById(id)) {
            return repo.findById(id).get();
        } else {
            return new Student();
        }
    }

    public Student updateStudentGeneralInfo(long id, String name, String surname) {
        if (repo.existsById(id)) {
            Student studentToUpdate = repo.findById(id).get();
            studentToUpdate.setName(name);
            studentToUpdate.setSurname(surname);
            return studentToUpdate;
        } else {
            return new Student();
        }
    }

    public List<Student> deleteAStudent(long id){
        repo.deleteById(id);
        return repo.findAll();
    }
}
