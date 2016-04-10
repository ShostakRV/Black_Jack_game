package com.my.applicatiom.black.jack.test.dao;

import com.my.applicatiom.black.jack.test.config.IntegrationTestConfig;
import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.config.ServerConfig;
import com.my.application.black.jack.server.dao.GameCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    public void baseCreationTest() {
        GameCard card1 = new UserCard();
        card1.setCard(Card.CLUBS_2);
        ;

        card1.setOrder(0);
        gameCardRepository.save(card1);
        gameCardRepository.findAll();
    }
}
