package com.argus.alishevspring.services;

import com.argus.alishevspring.models.Book;
import com.argus.alishevspring.models.Person;
import com.argus.alishevspring.repositories.BooksRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public Page<Book> index(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }
    public List<Book> indexSorted() {return  booksRepository.findByOrderByAgeOfPublishment();}

    public Page<Book> index(String title, Pageable pageable) {
        return booksRepository.findByTitle(title, pageable);
    }

    public Book find(String title) {
        return booksRepository.findByTitleIgnoreCaseStartsWith(title).stream().findFirst()
                .orElseGet(Book::new);
    }

    public Book show(int bookId) {
        return booksRepository.findById(bookId).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int bookId, Book updatedBook) {
        updatedBook.setBookId(bookId);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int bookId) {
        booksRepository.deleteById(bookId);
    }

    //TODO: else return not null?
    public Person showPerson(int bookId) {
        Optional<Book> book = booksRepository.findById(bookId);
        if (book.isPresent()) {
            Person owner = book.get().getOwner();
            Hibernate.initialize(owner);
            return owner;
        } else {
            return null;
        }
    }

    @Transactional
    public void assignPerson(int bookId, Person person) {
        booksRepository.findById(bookId).orElseThrow().setOwner(person);
    }

    @Transactional
    public void releasePerson(int bookId) {
        booksRepository.findById(bookId).orElseThrow().setOwner(null);
    }
}
