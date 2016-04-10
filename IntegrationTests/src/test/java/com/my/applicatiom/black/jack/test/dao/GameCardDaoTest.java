package com.my.applicatiom.black.jack.test.dao;

import com.my.applicatiom.black.jack.test.config.IntegrationTestConfig;
import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.server.config.ServerConfig;
import com.my.application.black.jack.server.dao.GameCardRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created: Shostak Roman
 * Date: 10.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextHierarchy({@ContextConfiguration(classes = {ServerConfig.class, IntegrationTestConfig.class})})
@Transactional
public class GameCardDaoTest {
    @Autowired
    private GameCardRepository gameCardRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void baseCreationTest() {
        GameCard card1 = new GameCard();
        card1.setCard(Card.CLUBS_2);
        card1.setCardType(GameCard.CardType.USER);


        card1.setOrder(0);
        gameCardRepository.save(card1);
        gameCardRepository.findAll();
    }

    @Test
    public void test3() {
        User user = new User("test2@email.com", "$2a$10$x9vXeDsSC2109FZfIJz.pOZ4dJ056xBpbesuMJg3jZ.ThQkV119tS");
        user.setAmount(new BigDecimal(5000));
        userRepository.save(user);
        assertEquals(userRepository.findAll().size(), 2);
    }
}
