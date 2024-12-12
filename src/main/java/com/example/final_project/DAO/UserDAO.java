package com.example.final_project.DAO;

import com.example.final_project.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends DAO{

    public void saveUser(User user) {
        Transaction t = null;
        try (Session session = getSession()) {
            t = session.beginTransaction();
            session.saveOrUpdate(user);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }

    public boolean checkLogin(User user) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("username"), user.getUsername()));
        User result = getSession().createQuery(criteria).uniqueResult();
        if (user.getPassword().equals(result.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public User getUserByUsername(String username) {
        Query query = getSession().createQuery("from User where username = :username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    public User getUserById(long id) {
        Query query = getSession().createQuery("from User where userId = :id");
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    public void updateUser(User user) {
        Transaction t = null;
        try (Session session = getSession()) {
            t = session.beginTransaction();
            session.merge(user);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }
}
