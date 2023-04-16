package com.argus.alishevspring.dao;

import com.argus.alishevspring.models.Book;
import com.argus.alishevspring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int personId) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

//    public Optional<Person> show(String email) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullname, age_of_birth) VALUES (?, ?)",
                person.getFullName(),
                person.getAgeOfBirth());
    }

    public void update(int person_id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fullname=?, age_of_birth=? WHERE  person_id=?",
                updatedPerson.getFullName(),
                updatedPerson.getAgeOfBirth(),
                person_id);
    }

    public void delete(int person_id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }

    public List<Book> showBooks(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }
}