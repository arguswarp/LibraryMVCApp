package com.argus.alishevspring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30 characters")
    private String name;
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 1, max = 30, message = "Author name should be between 1 and 30 characters")
    private String author;
    @Min(value = 0, message = "Year should be be greater than 0")
    private int yearOfPublishment;

    public Book(int id, String name, String author, int yearOfPublishment) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublishment = yearOfPublishment;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublishment() {
        return yearOfPublishment;
    }

    public void setYearOfPublishment(int yearOfPublishment) {
        this.yearOfPublishment = yearOfPublishment;
    }
}
