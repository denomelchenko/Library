package com.denomelchenko.library.LibraryBoot.util;

import com.denomelchenko.library.LibraryBoot.models.User;
import com.denomelchenko.library.LibraryBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (!user.getFullName().isEmpty()) {
            String[] splits = user.getFullName().split(" ");
            if (splits.length >= 2) {
                if (!Character.isUpperCase(splits[0].codePointAt(0)) ||
                        !Character.isUpperCase(splits[1].codePointAt(0))) {
                    errors.rejectValue("fullName", "", "Each word should start from capital letter");
                } else {
                    Optional<User> optionalUser = userService.findUserByFullName(user.getFullName());
                    if (optionalUser.isPresent() && user.getId() != optionalUser.get().getId()) {
                        errors.rejectValue("fullName", "", "User is already registered");
                    }
                }
            } else {
                errors.rejectValue("fullName", "", "Please write full name");
            }
        }
    }
}
