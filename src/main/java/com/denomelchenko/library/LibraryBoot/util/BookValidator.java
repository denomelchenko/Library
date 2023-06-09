package com.denomelchenko.library.LibraryBoot.util;

import com.denomelchenko.library.LibraryBoot.models.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (!book.getAuthor().isEmpty()) {
            String[] splits = book.getAuthor().split(" ");
            if (splits.length != 2) {
                errors.rejectValue("author", "", "2 words(name + surname)");
            } else if (!Character.isUpperCase(splits[0].codePointAt(0)) ||
                    !Character.isUpperCase(splits[1].codePointAt(0))) {
                errors.rejectValue("author", "", "Name Surname(each word should start from capital letter)");
            }
        }
    }
}
