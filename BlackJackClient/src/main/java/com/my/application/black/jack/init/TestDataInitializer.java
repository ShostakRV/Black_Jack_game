package com.my.application.black.jack.init;


import com.my.application.black.jack.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import java.sql.Time;
import java.util.Date;

/**
 *
 * This is a initializing bean that inserts some test data in the database. It is only active in
 * the development profile, to see the data login with user123 / Password2 and do a search starting on
 * 1st of January 2015.
 *
 */

public class TestDataInitializer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @PostConstruct
    public void postConstruct(){
        System.out.print("");
    }

    public void init() throws Exception {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User("test@email.com", "$2a$10$x9vXeDsSC2109FZfIJz.pOZ4dJ056xBpbesuMJg3jZ.ThQkV119tS" );

        session.persist(user);

        transaction.commit();
    }
}
