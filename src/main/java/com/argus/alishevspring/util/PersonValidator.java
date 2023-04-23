package com.argus.alishevspring.util;

import com.argus.alishevspring.dao.PersonDAO;
import com.argus.alishevspring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        Person person = (Person) o;
//
//        if (personDAO.show(person.getEmail()).isPresent()) {
//            errors.rejectValue("email", "", "This email has already been taken");
//        }
    }
}
