package com.example.final_project.controller;

import com.example.final_project.DAO.ItemDAO;
import com.example.final_project.model.Item;
import com.example.final_project.validator.SearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MainController {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    SearchValidator searchValidator;

    @GetMapping("/main-page")
    public String mainPage(ModelMap model) {
        List<Item> items = itemDAO.getAllItems();
        model.addAttribute("items", items);
        return "main-page";
    }

    @GetMapping("/search")
    public String search(ModelMap model, @RequestParam("query") String keyword) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]+$");
        Matcher matcher = pattern.matcher(keyword);
        if (!matcher.find() || keyword.length() == 0) {
            model.addAttribute("errors", "Search keyword is empty and it only accepts a-z, A-Z, 0-9, - and _");
            return "main-page";
        }

        List<Item> items = itemDAO.searchItem(keyword);
        model.addAttribute("items", items);
        return "main-page";
    }
}
