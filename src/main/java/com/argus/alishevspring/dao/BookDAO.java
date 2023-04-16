package com.argus.alishevspring.dao;

import com.argus.alishevspring.models.Book;
import com.argus.alishevspring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int bookId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, age_of_publishment) VALUES (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getAgeOfPublishment());
    }

    public void update(int bookId, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, age_of_publishment=? WHERE  book_id=?",
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getAgeOfPublishment(),
                bookId);
    }

    public Person showPerson(int bookId) {
        return jdbcTemplate.query("SELECT Person.fullname FROM Book LEFT JOIN Person ON Person.person_id=Book.person_id WHERE Book.book_id=?", new Object[]{bookId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

}
