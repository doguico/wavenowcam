package com.wavenowcam.dao.impl;

import com.wavenowcam.dao.UserDAO;
import com.wavenowcam.dos.User;
import com.wavenowcam.utils.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author guidocorazza
 */
@Component
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public UserDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User getUserByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User u where u.email = :email").setParameter("email", email).list();
        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }
        session.getTransaction().commit();
        session.close();

        return user;
    }

}
