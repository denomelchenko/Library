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
    private int yearOfBirthday;

    public User() {
    }

    public User(int id, String fullName, int yearOfBirthday) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirthday = yearOfBirthday;
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

    public int getYearOfBirthday() {
        return yearOfBirthday;
    }

    public void setYearOfBirthday(int yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
    }
}
