package com.argus.alishevspring.repositories;

import com.argus.alishevspring.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    List<Person> findByFullName(String fullname);

    List<Person> findByFullNameOrderByAgeOfBirth(String fullname);

    List<Person> findByAgeOfBirth(int ageOfBirth);

    List<Person> findByFullNameStartingWith(String startingWith);

    List<Person> findByFullNameOrAgeOfBirth(String fullname, int ageOfBirth);
    Page<Person> findByFullName(String fullname, Pageable pageable);
}
