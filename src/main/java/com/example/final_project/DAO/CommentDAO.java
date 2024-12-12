package com.example.final_project.DAO;

import com.example.final_project.model.Comment;
import com.example.final_project.model.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAO extends DAO{

    public List<Comment> getComments(Item item) {
        Query q = getSession().createQuery("from Comment where item= :item");
        q.setParameter("item", item);
        List<Comment> comments = q.list();
        return comments;
    }

    public void saveComment(Comment comment) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.save(comment);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

}
