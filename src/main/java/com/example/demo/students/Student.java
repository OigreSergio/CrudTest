package com.example.demo.students;

import jakarta.persistence.*;

@Entity
@Table
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private boolean isWorking;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surename) {
        this.surname = surename;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
    public Student(){}

    public Student(long id, String name, String surename, boolean isWorking) {
        this.id = id;
        this.name = name;
        this.surname = surename;
        this.isWorking = isWorking;
    }
}
