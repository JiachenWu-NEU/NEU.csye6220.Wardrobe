package com.example.final_project.controller;

import com.example.final_project.DAO.OrderDAO;
import com.example.final_project.model.Order;
import com.example.final_project.model.User;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderDAO orderDAO;

    @GetMapping("/order")
    public String order(ModelMap model, HttpSession session) {
        List<Order> orders = orderDAO.getYourOrder((User) session.getAttribute("user"));
        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/order-detail")
    public String orderDetail(ModelMap model, @RequestParam("id") long orderId) {
        Order order = orderDAO.getOrderById(orderId);
        model.addAttribute("order", order);
        return "order-detail";
    }

    @GetMapping("/admin-order")
    public String adminOrder(ModelMap model) {
        List<Order> orders = orderDAO.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin-order";
    }

    @GetMapping("/admin-order-detail")
    public String adminOrderDetail(ModelMap model, @RequestParam("id") long orderId) {
        Order order = orderDAO.getOrderById(orderId);
        model.addAttribute("order", order);
        return "admin-order-detail";
    }
}
