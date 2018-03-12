/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wavenowcam.dao.impl;

import com.wavenowcam.dao.BeachDAO;
import com.wavenowcam.dos.Beach;
import com.wavenowcam.utils.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 *
 * @author guidocorazza
 */
@Component
public class BeachDAOImpl implements BeachDAO {

    private final SessionFactory sessionFactory;

    private BeachDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public Long saveBeach(Beach beach) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(beach);

        transaction.commit();
        session.close();

        return beach.getId();
    }

    @Override
    public Beach getBeachById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Beach beach = (Beach) session.get(Beach.class, id);

        session.getTransaction().commit();
        session.close();

        return beach;
    }

    @Override
    public void deleteBeach(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Beach beach = (Beach) session.get(Beach.class, id);
        session.delete(beach);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Beach getBeachByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Beach> results = session.createQuery("from Beach b where b.name = :name").setParameter("name", name).list();
        Beach beach = null;
        if (results.size() > 0){
            beach = results.get(0);
        }

        session.getTransaction().commit();
        session.close();

        return beach;
    }

    @Override
        public List<Beach> getAll() {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();

        List<Beach> beaches = session.createQuery("from Beach").list();

        session.getTransaction().commit();
        session.close();

        return beaches;
    }
}
