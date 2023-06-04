package com.denomelchenko.library.models;

import javax.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "Must not be empty")
    @Size(min = 2, max = 35, message = "Name must be between 2 and 30 characters")
    private String name;

    @Min(value = 0, message = "Age should be bigger than 0")
    @Max(value = 200, message = "Age should be less than 200")
    private int age;
    @NotEmpty(message = "Must not be empty")
    @Email(message = "Email is not correct")
    private String email;

    public Person() {

    }

    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
