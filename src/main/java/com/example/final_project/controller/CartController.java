package com.example.final_project.controller;

import com.example.final_project.DAO.CartItemDAO;
import com.example.final_project.DAO.ItemDAO;
import com.example.final_project.DAO.OrderDAO;
import com.example.final_project.model.*;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    CartItemDAO cartItemDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    private ItemDAO itemDAO;

    @GetMapping("/cart")
    public String cart(ModelMap model, HttpSession session) {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems = cartItemDAO.getAllCartItems((User) session.getAttribute("user"));
        session.setAttribute("cartItems", cartItems);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/cart")
    public String addCartItem(ModelMap model, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems.isEmpty()) {
            model.addAttribute("message", "No cart items");
            return "error";
        }
        session.removeAttribute("cartItems");
        List<OrderItem> orderItems = new ArrayList<>();
        User user = (User) session.getAttribute("user");
        Order order = new Order();

        order.setUser(user);
        order.setAddress(user.getAddress());
        order.setDate((new Date()).toString());
        for (CartItem cartItem : cartItems) {
            if (cartItem.getAmount() > ((Item) itemDAO.getItemById(cartItem.getItemId())).getStock()) {
                model.addAttribute("message", "For item" + cartItem.getName() + ", stock is not enough");
                return "error";
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(cartItem.getItemId());
            orderItem.setName(cartItem.getName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setPicture(cartItem.getPicture());
            orderItem.setAmount(cartItem.getAmount());
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            Item item = itemDAO.getItemById(cartItem.getItemId());
            item.setStock(item.getStock() - cartItem.getAmount());
            itemDAO.saveItem(item);
        }
        order.setOrderItems(orderItems);
        orderDAO.saveOrder(order);
        cartItemDAO.deleteCartItem(user);
        return "order-success";
    }

    @GetMapping("/deleteCartItem")
    public String deleteCartItem(@RequestParam("id") long cartItemId, HttpSession session) {
        cartItemDAO.removeCartItem(cartItemId);
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        session.removeAttribute("cartItems");
        for (CartItem cartItem : cartItems) {
            if (cartItem.getItemId() == cartItemId) {
                cartItems.remove(cartItem);
            }
        }
        session.setAttribute("cartItems", cartItems);
        return "redirect:/cart";
    }

    @PostMapping("/updateCartItemAmount")
    public String updateCartItemAmount(@RequestParam("id") long cartItemId, @RequestParam("amount") int amount) {
        CartItem cartItem = cartItemDAO.getCartItemById(cartItemId);
        cartItem.setAmount(amount);
        cartItemDAO.updateAmount(cartItem);
        return "redirect:/cart";
    }
}
