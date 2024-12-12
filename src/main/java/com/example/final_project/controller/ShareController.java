package com.example.final_project.controller;

import com.example.final_project.DAO.ShareDAO;
import com.example.final_project.DTO.ShareDTO;
import com.example.final_project.model.Share;
import com.example.final_project.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ShareController {

    @Autowired
    ShareDAO shareDAO;

    @GetMapping("/community")
    public String community(ModelMap model) {
        List<Share> shareList = shareDAO.getAllShare();
        model.addAttribute("shareList", shareList);
        return "community";
    }

    @GetMapping("/publish-share")
    public String publishShare(ModelMap model, HttpSession session, ShareDTO shareDTO) {
        model.addAttribute("share", shareDTO);
        return "publish-share";
    }

    @PostMapping("/publish-share")
    public String handleForm(HttpSession session, @ModelAttribute ShareDTO shareDTO) {
        Share share = new Share();
        User user = (User) session.getAttribute("user");
        share.setUser(user);
        share.setTitle(shareDTO.getTitle());
        share.setContent(shareDTO.getContent());
        share.setDate((new Date()).toString());
        shareDAO.saveShare(share);

        share.setPicture("http://localhost:8080/" + "s" + share.getShareId() + ".jpg");
        String path = System.getProperty("user.home") + "/Desktop/detailpictures";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        path = path + File.separator + "s" + share.getShareId() + ".jpg";
        try {
            MultipartFile picture = shareDTO.getPicture();
            file = new File(path);
            picture.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        shareDAO.saveShare(share);

        return "publish-success";
    }

    @GetMapping("/admin-community")
    public String adminCommunity(ModelMap model) {
        List<Share> shareList = shareDAO.getAllShare();
        model.addAttribute("shareList", shareList);
        return "admin-community";
    }

    @GetMapping("/deleteShare")
    public String deleteShare(@RequestParam("id") long shareId) {
        shareDAO.deleteShare(shareId);
        return "redirect:/admin-community";
    }
}
