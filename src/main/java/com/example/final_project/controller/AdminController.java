package com.example.final_project.controller;

import com.example.final_project.DAO.ItemDAO;
import com.example.final_project.DTO.ItemDTO;
import com.example.final_project.model.Item;
import com.example.final_project.validator.ItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    ItemValidator itemValidator;

    @GetMapping("/admin")
    public String admin(ModelMap model) {
        List<Item> items = itemDAO.getAllItems();
        model.addAttribute("items", items);
        return "admin";
    }

    @GetMapping("/delete")
    public String delete(ModelMap model, @RequestParam("id") long itemId) {
        itemDAO.removeItemById(itemId);
        return "redirect:/admin";
    }

    @GetMapping("/modify")
    public String modify(ModelMap model, @RequestParam("id") long itemId) {
        Item item = itemDAO.getItemById(itemId);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemId(itemId);
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setStock(item.getStock());
        itemDTO.setType(item.getType());
        model.addAttribute("item", itemDTO);
        return "modify";
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute("item") ItemDTO item, @RequestParam("id") long itemId, Errors errors) {
        itemValidator.validate(item, errors);
        if (errors.hasErrors()) {
            return "modify";
        }

        Item origin = itemDAO.getItemById(itemId);
        origin.setName(item.getName());
        origin.setPrice(item.getPrice());
        origin.setDescription(item.getDescription());
        origin.setStock(item.getStock());
        origin.setType(item.getType());

        itemDAO.saveItem(origin);
        MultipartFile picture = item.getPicPath();
        if (picture != null) {
            String path = System.getProperty("user.home") + "/Desktop/detailpictures";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            path = path + File.separator + itemId + ".jpg";
            try {
                file = new File(path);
                picture.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "modify-success";
    }

    @GetMapping("/add-item")
    public String addItem(ModelMap model, ItemDTO itemDTO) {
        model.addAttribute("item", itemDTO);
        return "add-item";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute("item") ItemDTO itemDTO, Errors errors) {
        itemValidator.validate(itemDTO, errors);
        if (errors.hasErrors()) {
            return "add-item";
        }

        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice());
        item.setDescription(itemDTO.getDescription());
        item.setStock(itemDTO.getStock());
        item.setType(itemDTO.getType());
        itemDAO.saveItem(item);

        item.setPicPath("http://localhost:8080/" + item.getItemId() + ".jpg");
        MultipartFile picture = itemDTO.getPicPath();
        if (picture != null) {
            String path = System.getProperty("user.home") + "/Desktop/detailpictures";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            path = path + File.separator + item.getItemId() + ".jpg";
            try {
                file = new File(path);
                picture.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        itemDAO.saveItem(item);
        return "add-item-success";
    }
}
