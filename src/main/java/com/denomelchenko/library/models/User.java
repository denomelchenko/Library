package com.denomelchenko.library.models;

import javax.validation.constraints.*;

public class User {
    private final static String NOT_EMPTY_MESSAGE = "Could not be empty";
    private int id;
    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 5, max = 110, message = "Full name must be between 5 and 110 characters")
    private String fullName;
    @Min(value = 1920, message = "Birth year should be bigger than 1920")
    @Max(value = 2023, message = "Birth year should be less than 2024")
    private int yearOfBirth;

    public User() {
    }

    public User(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
