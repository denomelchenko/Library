package com.denomelchenko.library.LibraryBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "person")
public class User {
    private final static String NOT_EMPTY_MESSAGE = "Could not be empty";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 5, max = 110, message = "Full name must be between 5 and 110 characters")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 1920, message = "Birth year should be bigger than 1920")
    @Max(value = 2025, message = "Birth year should be less than 2026")
    private int yearOfBirth;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public User() {
    }

    public User(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
