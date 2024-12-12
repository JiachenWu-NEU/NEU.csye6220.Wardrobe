package com.example.final_project.controller;

import com.example.final_project.DAO.CartItemDAO;
import com.example.final_project.DAO.CommentDAO;
import com.example.final_project.DAO.ItemDAO;
import com.example.final_project.model.CartItem;
import com.example.final_project.model.Comment;
import com.example.final_project.model.Item;
import com.example.final_project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CartItemDAO cartItemDAO;

    @Autowired
    CommentDAO commentDAO;

    @GetMapping("/item-detail")
    public String detail(ModelMap model, Item item, @RequestParam("id") long id) {
        item = itemDAO.getItemById(id);
        model.addAttribute("item", item);
        List<Comment> comments = commentDAO.getComments(item);
        model.addAttribute("comments", comments);
        return "item-detail";
    }

    @PostMapping("/item-detail")
    public String handleForm(@RequestParam("amount") int amount, @RequestParam("itemId") long id, ModelMap model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        CartItem cartItem = cartItemDAO.checkIfExist(id, user);
        if (cartItem != null) {
            cartItem.setAmount(cartItem.getAmount() + amount);
            cartItemDAO.save(cartItem);
            return "add-success";
        }

        System.out.println(id);
        cartItem = new CartItem();
        Item item = itemDAO.getItemById(id);
        cartItem.setItemId(id);
        cartItem.setAmount(amount);
        cartItem.setName(item.getName());
        cartItem.setPrice(item.getPrice());
        cartItem.setPicture(item.getPicPath());
        cartItem.setUser((User) session.getAttribute("user"));

        cartItemDAO.save(cartItem);
        return "add-success";
    }
}
