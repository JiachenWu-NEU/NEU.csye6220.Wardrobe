package com.example.final_project.validator;

import com.example.final_project.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!(target instanceof User)) {
            throw new IllegalArgumentException("Target object must be of type User");
        }
        User user = (User) target;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+$");
        Matcher matcher = pattern.matcher(user.getUsername());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "", "username cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "password cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "", "address cannot be empty");

        if (!matcher.find()) {
            errors.rejectValue("username", "", "username only accept a-z, A-Z, 0-9, _ and -");
        }

        matcher = pattern.matcher(user.getPassword());
        if (!matcher.find()) {
            errors.rejectValue("password", "", "password only accept a-z, A-Z, 0-9, _ and -");
        }

    }
}
