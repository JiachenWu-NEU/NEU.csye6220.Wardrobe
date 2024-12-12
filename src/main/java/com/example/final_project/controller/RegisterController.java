package com.example.final_project.controller;

import com.example.final_project.DAO.UserDAO;
import com.example.final_project.model.User;
import com.example.final_project.validator.UserValidator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserDAO userDAO;

    @GetMapping("/register")
    public String register(User user, ModelMap map) {
        map.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String handleForm(@ModelAttribute User user, ModelMap map, Errors errors) {
        userValidator.validate(user, errors);
        if (errors.hasErrors()) {
            return "register";
        }

        userDAO.saveUser(user);
        return "redirect:/login";
    }
}
