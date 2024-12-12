package com.example.final_project.DAO;

import com.example.final_project.model.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAO extends DAO {

    public List<Item> getAllItems() {
        try (Session session = getSession()) {
            Query query = session.createQuery("from Item", Item.class);
            List<Item> items = query.list();
            return items;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Item getItemById(long id) {
        try (Session session = getSession()) {
            Query query = session.createQuery("from Item where itemId = :id", Item.class);
            query.setParameter("id", id);
            Item item = (Item) query.uniqueResult();
            return item;
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeItemById(long id) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Item where itemId = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public void saveItem(Item item) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(item);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public List<Item> searchItem(String keyword) {
        try (Session session = getSession()) {
            Query query = session.createQuery("from Item where name like :keyword");
            query.setParameter("keyword", "%" + keyword + "%");
            List<Item> items = query.list();
            return items;
        }
    }
}
