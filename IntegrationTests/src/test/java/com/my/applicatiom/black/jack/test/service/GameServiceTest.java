package com.my.applicatiom.black.jack.test.service;

import com.my.applicatiom.black.jack.test.config.IntegrationTestConfig;
import com.my.application.black.jack.model.AmountHistory;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.server.config.ServerConfig;
import com.my.application.black.jack.server.dao.AmountHistoryRepository;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.service.GameService;
import com.my.application.black.jack.server.service.UserService;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created: Shostak Roman
 * Date: 03.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextHierarchy({@ContextConfiguration(classes = {ServerConfig.class, IntegrationTestConfig.class})})
@Transactional
public class GameServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private AmountHistoryRepository historyRepository;

    @Test
    public void testGameCreation() throws Exception {
        User user = userService.findByEmail("test@email.com");
        BigDecimal beforeMoney = user.getAmount();
        BigDecimal rate = new BigDecimal(100);
        GameDto gameDto = gameService.createGameForUser(user.getEmail(), rate);
        assertTrue(gameDto.getId() != 0);
        Game game = gameRepository.findOne(gameDto.getId());
        assertTrue(game != null);
        assertTrue(game.getUserCard1() != null);
        assertTrue(game.getUserCard2() != null);
        assertTrue(game.getCroupierCard1() != null);
        assertTrue(game.getCroupierCard2() != null);
        user = userService.findByEmail("test@email.com");
        BigDecimal afterMoney = user.getAmount();
        BigDecimal expectedMoneyValue = beforeMoney.subtract(rate);
        assertEquals(expectedMoneyValue, afterMoney);
        List<AmountHistory> histories = historyRepository.findByGameId(game.getId());
        assertTrue(histories != null && histories.size() == 1);
        AmountHistory amountHistory = histories.get(0);
        assertEquals(beforeMoney, amountHistory.getBefore());
        assertEquals(afterMoney, amountHistory.getAfter());
    }

}
