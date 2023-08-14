package com.argus.alishevspring.repositories;

import com.argus.alishevspring.models.Book;
import com.argus.alishevspring.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByOwner(Person owner);
    List<Book> findByOrderByAgeOfPublishment();

    List<Book> findByTitle(String title);

    List<Book> findByTitleIgnoreCaseStartsWith(String key);

    Page<Book> findByTitle(String title, Pageable pageable);
}
