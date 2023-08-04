package com.argus.alishevspring.services;

import com.argus.alishevspring.models.Book;
import com.argus.alishevspring.models.Person;
import com.argus.alishevspring.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int personId) {
        return peopleRepository.findById(personId).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int person_id, Person updatedPerson) {
        updatedPerson.setPersonId(person_id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int person_id) {
        peopleRepository.deleteById(person_id);
    }

    public List<Book> showBooks(int personId) {
        Optional<Person> person = peopleRepository.findById(personId);

        if (person.isPresent()) {
            List<Book> books = person.get().getBooks();
            Hibernate.initialize(books);
            return books;
        } else {
            return Collections.emptyList();
        }
    }
}
