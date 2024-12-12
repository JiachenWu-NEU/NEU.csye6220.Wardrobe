package com.example.final_project.validator;

import com.example.final_project.DTO.ItemDTO;
import com.example.final_project.model.Item;
import com.example.final_project.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ItemDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ItemDTO itemDTO = (ItemDTO) target;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+$");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "name cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "price cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "type cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stock", "", "stock cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "description cannot be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "picPath", "", "picture cannot be empty");

        Matcher matcher = pattern.matcher(itemDTO.getName());
        if (!matcher.find()) {
            errors.rejectValue("name", "", "name only accept a-z, A-Z, 0-9, _ and -");
        }

        if (itemDTO.getPrice() <= 0) {
            errors.rejectValue("price", "", "price cannot be less than 0");
        }

        if (itemDTO.getStock() <= 0) {
            errors.rejectValue("stock", "", "stock cannot be less than 0");
        }
    }
}
