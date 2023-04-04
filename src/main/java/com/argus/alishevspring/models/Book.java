package com.argus.alishevspring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int bookId;
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 30, message = "Title should be between 1 and 30 characters")
    private String title;
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 1, max = 30, message = "Author name should be between 1 and 30 characters")
    private String author;
    @Min(value = 0, message = "Age of publishment should be be greater than 0")
    private int ageOfPublishment;

    public Book(int bookId, String title, String author, int ageOfPublishment) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ageOfPublishment = ageOfPublishment;
    }

    public Book() {

    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public int getAgeOfPublishment() {
        return ageOfPublishment;
    }

    public void setAgeOfPublishment(int ageOfPublishment) {
        this.ageOfPublishment = ageOfPublishment;
    }
}
