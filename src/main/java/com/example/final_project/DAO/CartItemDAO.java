package com.example.final_project.DAO;

import com.example.final_project.model.CartItem;
import com.example.final_project.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartItemDAO extends DAO{

    public void save(CartItem cartItem) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(cartItem);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public List<CartItem> getAllCartItems(User user) {
        Query q = getSession().createQuery("from CartItem where user = :user", CartItem.class);
        q.setParameter("user", user);
        List<CartItem> cartItems = q.list();
        return cartItems;
    }

    public void deleteCartItem(User user) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            Query q = session.createQuery("delete from CartItem where user = :user");
            q.setParameter("user", user);
            q.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }

    }

    public void removeCartItem(long cartItemId) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            Query q = session.createQuery("delete from CartItem where cartItemId = :cartItemId");
            q.setParameter("cartItemId", cartItemId);
            q.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public CartItem getCartItemById(long cartItemId) {
        Query q = getSession().createQuery("from CartItem where cartItemId = :cartItemId");
        q.setParameter("cartItemId", cartItemId);
        return (CartItem) q.uniqueResult();
    }

    public void updateAmount(CartItem cartItem) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.merge(cartItem);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public CartItem checkIfExist(long itemId, User user) {
        Query q = getSession().createQuery("from CartItem where itemId = :itemId and user = :user");
        q.setParameter("itemId", itemId);
        q.setParameter("user", user);
        return (CartItem) q.uniqueResult();
    }
}
