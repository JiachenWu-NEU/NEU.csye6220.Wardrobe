package com.example.final_project.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long orderItemId;
    long itemId;
    String name;
    int amount;
    double price;
    String picture;

    @ManyToOne
    @JoinColumn(name = "orderId")
    Order order;

    public long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", itemId=" + itemId +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", order=" + order +
                '}';
    }
}
