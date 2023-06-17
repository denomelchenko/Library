package com.denomelchenko.library.models;

import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

public class Book {
    private final static String NOT_EMPTY_MESSAGE = "Could not be empty";

    private int id;
    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 3, max = 100, message = "Should be between 3 and 100")
    private String title;
    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 5, max = 110, message = "Should be between 5 and 110")
    private String author;

    @NotNull(message = NOT_EMPTY_MESSAGE)
    @Min(value = 1250, message = "Year should be bigger than 1250")
    @Max(value = 2023, message = "Year should be less than 2023")
    private int year;

    public Book() {
    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
