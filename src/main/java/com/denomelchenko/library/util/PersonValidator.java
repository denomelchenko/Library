package com.denomelchenko.library.util;

import com.denomelchenko.library.dao.PersonDAO;
import com.denomelchenko.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Neil Alishev
 */
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
        Person person = (Person) o;


        if (personDAO.show(person.getEmail()).isPresent() &&
                person.getId() != personDAO.show(person.getEmail()).get().getId()) {
            // поле, код ошибки, сообщение ошибки
            errors.rejectValue("email", "", "This email is already in use");
        }

        // Проверяем, что у человека имя начинается с заглавной буквы
        // Если имя не начинается с заглавной буквы - выдаем ошибку
        if (!person.getName().isEmpty() && !Character.isUpperCase(person.getName().codePointAt(0)))
            errors.rejectValue("name", "", "Name should start with a capital letter");
    }
}