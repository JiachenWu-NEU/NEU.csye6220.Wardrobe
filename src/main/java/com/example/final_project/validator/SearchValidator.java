package com.example.final_project.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SearchValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        String keyword = (String) target;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+$");
        Matcher matcher = pattern.matcher(keyword);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "keyword", "", "keyword is empty");

        if (!matcher.matches()) {
            errors.rejectValue("keyword", "", "keyword only accept a-z, A-Z, 0-9, _ and -");
        }
    }
}
