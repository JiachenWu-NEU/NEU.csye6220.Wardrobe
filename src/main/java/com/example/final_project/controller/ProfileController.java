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
public class ProfileController {

    @Autowired
    UserDAO userDAO;

    @GetMapping("/profile")
    public String profile(ModelMap model, User user, HttpSession session) {
        user = (User) session.getAttribute("user");
        user = userDAO.getUserById(user.getUserId());
        session.removeAttribute("user");
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String handleForm(ModelMap model, @ModelAttribute User user, HttpSession session) {
        User sessionUser = (User) session.getAttribute("user");
        User origin = userDAO.getUserById(sessionUser.getUserId());
        if (origin.getUsername().equals(user.getUsername()) && origin.getPassword().equals(user.getPassword())) {
            origin.setAddress(user.getAddress());
            userDAO.updateUser(origin);
            return "redirect:/profile";
        } else {
            origin.setUsername(user.getUsername());
            origin.setPassword(user.getPassword());
            origin.setAddress(user.getAddress());
            userDAO.updateUser(origin);
            session.invalidate();
            return "redirect:/login";
        }
    }
}
