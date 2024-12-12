package com.example.final_project.DAO;

import com.example.final_project.model.Order;
import com.example.final_project.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAO extends DAO{

    public void saveOrder(Order order){
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public List<Order> getYourOrder(User user){
        Query query = getSession().createQuery("from Order where user= :user");
        query.setParameter("user", user);
        List<Order> orders = query.list();
        return orders;
    }

    public Order getOrderById(long id){
        Query query = getSession().createQuery("from Order where id= :id");
        query.setParameter("id", id);
        Order order = (Order) query.uniqueResult();
        return order;
    }

    public List<Order> getAllOrders(){
        Query query = getSession().createQuery("from Order");
        List<Order> orders = query.list();
        return orders;
    }
}
