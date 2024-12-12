package com.example.final_project.DAO;

import com.example.final_project.model.Share;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShareDAO extends DAO{

    public List<Share> getAllShare(){
        Query query = getSession().createQuery("from Share", Share.class);
        List<Share> shareList = query.list();
        return shareList;
    }

    public void saveShare(Share share){
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(share);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }

    public void deleteShare(long shareId){
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Share where shareId= :shareId");
            query.setParameter("shareId", shareId);
            query.executeUpdate();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
    }
}
