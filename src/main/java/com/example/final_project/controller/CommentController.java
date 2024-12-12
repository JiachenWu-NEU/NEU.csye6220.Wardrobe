package com.example.final_project.controller;

import com.example.final_project.DAO.CommentDAO;
import com.example.final_project.DAO.ItemDAO;
import com.example.final_project.model.Comment;
import com.example.final_project.model.Item;
import com.example.final_project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    CommentDAO commentDAO;

    @Autowired
    ItemDAO itemDAO;

    @PostMapping("addComment")
    public String addComment(@RequestParam("itemId") long itemId, @RequestParam("content") String content, HttpSession session, ModelMap model) {
        if (content.equals("") || content == null) {
            model.addAttribute("message", "Please enter a comment");
            return "error";
        }

        User user = (User) session.getAttribute("user");
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setComment(content);
        comment.setDate((new Date()).toString());
        comment.setItem((Item) itemDAO.getItemById(itemId));

        commentDAO.saveComment(comment);
        return "redirect:/item-detail?id=" + itemId;
    }
}
