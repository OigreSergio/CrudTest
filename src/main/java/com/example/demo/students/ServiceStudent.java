package com.example.demo.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServiceStudent {

    @Autowired
    private RepositoryStudent repo;

    public Student activateWork(long id, boolean status){
        Student activateStudent = null;
        if(repo.existsById(id)){
            activateStudent = repo.getReferenceById(id);
            activateStudent.setWorking(status);
        }
        return activateStudent;
    }
    public Student createStudent ( Student student){
        return repo.save(student);
    }
}
