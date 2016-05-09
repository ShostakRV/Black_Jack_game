package com.my.application.black.jack.server.service.converter;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.model.cards.CardMask;
import com.my.application.black.jack.model.cards.CardName;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.service.dto.GameDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * Created: Shostak Roman
 * Date: 22.04.2016.
 */

public class GameConverterTest {
    private GameConverter gameConverter;
    private Game game;

    @Before
    public void setup() {
        gameConverter = new GameConverter();
        User user = new User();
        game = new Game();
        game.setRate(BigDecimal.TEN);
        game.setStart(LocalDateTime.now());
        game.setUser(user);
        game.setState(GameState.ON_PROGRESS);

        game.getGameCards().add(new UserCard(CardName._2, CardMask.CLUBS));
        game.getGameCards().add(new UserCard(CardName._3, CardMask.CLUBS));

        game.getGameCards().add(new CroupierCard(CardName._4, CardMask.CLUBS));
        game.getGameCards().add(new CroupierCard(CardName._5, CardMask.CLUBS));
    }

    @Test
    public void testNewGameConvert() {
        game.setState(GameState.ON_PROGRESS);

        GameDto gameDto = gameConverter.convert(game, 5, 9);
        assertEquals(1, gameDto.getCroupierCards().size());
        assertEquals(2, gameDto.getUserCards().size());
        assertEquals(game.getId(), gameDto.getId());
        assertEquals(game.getRate(), gameDto.getRate());
        assertEquals(game.getState().name(), gameDto.getGameStatus());
    }

    @Test
    public void testNewGameConvertPoints() {
        game.setState(GameState.USER_LOSE);

        GameDto gameDto = gameConverter.convert(game, 5, 9);
        assertEquals(2, gameDto.getUserCards().size());
        assertEquals(2, gameDto.getCroupierCards().size());
        assertEquals(5, gameDto.getUserPoints());
        assertEquals(9, gameDto.getCroupierPoints());
    }

    @Test
    public void testNewGameConvertPoints21() {
        game.setState(GameState.USER_WIN);

        game.getGameCards().add(new UserCard(CardName._10, CardMask.CLUBS));
        game.getGameCards().add(new UserCard(CardName._6, CardMask.CLUBS));

        GameDto gameDto = gameConverter.convert(game, 21, 9);
        assertEquals(4, gameDto.getUserCards().size());
        assertEquals(21, gameDto.getUserPoints());
        assertEquals(9, gameDto.getCroupierPoints());
    }


    @Test
    public void testFinishedGame() {
        GameDto gameDto;

        game.setState(GameState.USER_WIN);
        game.setFinish(LocalDateTime.now());

        gameDto = gameConverter.convert(game, 5, 9);
        assertEquals(2, gameDto.getCroupierCards().size());
        assertEquals(2, gameDto.getUserCards().size());
        assertEquals(game.getId(), gameDto.getId());
        assertEquals(game.getRate(), gameDto.getRate());
    }

}