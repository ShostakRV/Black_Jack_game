package com.my.applicatiom.black.jack.test.init;


import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.service.GameService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

/**
 * This is a initializing bean that inserts some test data in the database. It is only active in
 * the development profile, to see the data login with user123 / Password2 and do a search starting on
 * 1st of January 2015.
 */

public class TestDataInitializer {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private GameService gameService;

    @PostConstruct
    public void postConstruct() {
        System.out.print("");
    }

    public void init() throws Exception {

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User("test@email.com", "$2a$10$x9vXeDsSC2109FZfIJz.pOZ4dJ056xBpbesuMJg3jZ.ThQkV119tS");
        user.setAmount(new BigDecimal(5000));
        session.persist(user);
//        Game game = new Game();
//        game.setUser(user);
//        game.setRate(BigDecimal.ONE);
//        session.persist(game);
//        GameCard card1 = new UserCard();
//        card1.setCard(Card.CLUBS_2);
//        card1.setGame(game);
        transaction.commit();
    }
}
