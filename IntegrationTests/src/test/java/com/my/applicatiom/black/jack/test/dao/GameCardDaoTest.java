package com.my.applicatiom.black.jack.test.dao;

import com.my.applicatiom.black.jack.test.config.IntegrationTestConfig;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.cards.CardMask;
import com.my.application.black.jack.model.cards.CardName;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.config.ServerConfig;
import com.my.application.black.jack.server.dao.GameCardRepository;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.service.GameService;
import com.my.application.black.jack.server.service.dto.GameDto;
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
    @Autowired
    private GameService gameService;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void baseCreationTest() {
        GameDto gameDto = gameService.createGameForUser("test@email.com", BigDecimal.ONE);
        Game game = gameRepository.findOne(gameDto.getId());
        UserCard card1 = gameCardRepository.newUserCard();

        card1.setCardMask(CardMask.CLUBS);
        card1.setCardName(CardName._2);
        card1.setGame(game);
        card1.setSorting(0);

        game.getGameCards().add(card1);
        game = gameRepository.saveAndFlush(game);
        CroupierCard card2 = gameCardRepository.newCroupierCard();
        card2.setGame(game);
        card2.setSorting(0);
        card2.setCardMask(CardMask.CLUBS);
        card2.setCardName(CardName._6);

        game.getGameCards().add(card2);
        gameRepository.saveAndFlush(game);

        assertEquals(gameCardRepository.findAll().size(), 6);


    }

}
