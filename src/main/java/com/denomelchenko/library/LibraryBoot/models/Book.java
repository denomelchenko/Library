package com.denomelchenko.library.LibraryBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {
    private final static String NOT_EMPTY_MESSAGE = "Could not be empty";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 3, max = 100, message = "Should be between 3 and 100 characters")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = NOT_EMPTY_MESSAGE)
    @Size(min = 5, max = 100, message = "Should be between 5 and 100 characters")
    private String author;

    @Column(name = "year")
    @Min(value = 1250, message = "Year should be bigger than 0")
    @Max(value = 2023, message = "Year should be less than 2023")
    private int year;

    @Column(name = "was_taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date wasTakenAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @Transient
    private boolean isExpired;

    public Book() {
    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Date getWasTakenAt() {
        return wasTakenAt;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public void setWasTakenAt(Date wasTakenAt) {
        this.wasTakenAt = wasTakenAt;
    }
}
