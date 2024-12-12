package com.example.final_project.controller;

import com.example.final_project.DAO.UserDAO;
import com.example.final_project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    UserDAO userDAO;

    @GetMapping("/login")
    public String login(User user, ModelMap map) {
        map.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String handleForm(@ModelAttribute User user, ModelMap map, HttpSession session) {
        if (userDAO.checkLogin(user)) {
            session.setAttribute("user", userDAO.getUserByUsername(user.getUsername()));
            if (user.getUsername().equals("admin")) {
                return "redirect:/admin";
            }
            return "redirect:/main-page";
        } else {
            map.addAttribute("user", user);
            map.addAttribute("message", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
