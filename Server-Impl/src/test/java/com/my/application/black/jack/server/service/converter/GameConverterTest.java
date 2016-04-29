package com.my.application.black.jack.server.service.converter;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.User;
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

        game.getGameCards().add(new UserCard(Card.CLUBS_2));
        game.getGameCards().add(new UserCard(Card.CLUBS_3));

        game.getGameCards().add(new CroupierCard(Card.CLUBS_4));
        game.getGameCards().add(new CroupierCard(Card.CLUBS_5));
    }

    @Test
    public void testNewGameConvert() {
        game.setState(GameState.ON_PROGRESS);

        GameDto gameDto = gameConverter.convert(game);
        assertEquals(1, gameDto.getCroupierCards().size());
        assertEquals(2, gameDto.getUserCards().size());
        assertEquals(game.getId(), gameDto.getId());
        assertEquals(game.getRate(), gameDto.getRate());
        assertEquals(game.getState().name(), gameDto.getGameStatus());
    }


    @Test
    public void testFinishedGame() {
        GameDto gameDto;

        game.setState(GameState.USER_WIN);
        game.setFinish(LocalDateTime.now());

        gameDto = gameConverter.convert(game);
        assertEquals(2, gameDto.getCroupierCards().size());
        assertEquals(2, gameDto.getUserCards().size());
        assertEquals(game.getId(), gameDto.getId());
        assertEquals(game.getRate(), gameDto.getRate());
    }

}