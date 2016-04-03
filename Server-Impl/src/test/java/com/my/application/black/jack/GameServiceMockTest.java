package com.my.application.black.jack;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.service.AmountService;
import com.my.application.black.jack.server.service.CardGenerator;
import com.my.application.black.jack.server.service.GameServiceImpl;
import com.my.application.black.jack.server.service.converter.GameConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceMockTest {

    private static final Answer<Card> ANSWERS_FOR_CARD_GENERATOR = new Answer<Card>() {
        int count = 0;

        public Card answer(InvocationOnMock invocation) throws Throwable {
            switch (count) {
                case 0:
                    count++;
                    return Card.CLUBS_2;
                case 1:
                    count++;
                    return Card.CLUBS_3;
                case 2:
                    count++;
                    return Card.CLUBS_4;
                case 3:
                    count++;
                    return Card.CLUBS_5;
                default:
                    throw new RuntimeException("Unexpected call!");
            }
        }
    };
    private static final String USER_NAME = "Test";
    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private ApplicationContext applicationContext;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CardGenerator generator;
    @Mock
    private GameConverter gameConverter;
    @Mock
    private User user;
    @Mock
    private Game game;
    @Mock
    private Game savedGame;
    @Mock
    private AmountService amountService;

    @Before
    public void init() {
        when(gameRepository.saveAndFlush(game)).thenReturn(savedGame);
        when(gameRepository.newEntity()).thenReturn(game);
        when(generator.nextCard()).thenAnswer(ANSWERS_FOR_CARD_GENERATOR);

        when(applicationContext.getBean(CardGenerator.class)).thenReturn(generator);

        when(userRepository.findByEmail(USER_NAME)).thenReturn(user);

    }

    @Test
    public void test1() {
        Game t2 = gameRepository.saveAndFlush(game);
        assertTrue(savedGame == t2);
        verify(gameRepository).saveAndFlush(game);
    }

    @Test
    public void testGameCreation() {
        BigDecimal rate = new BigDecimal(100);
        gameService.createGameForUser(USER_NAME, rate);

        verify(game).setUser(user);
        verify(game).setRate(rate);
        verify(game).setUserCard1(Card.CLUBS_2);
        verify(game).setUserCard2(Card.CLUBS_3);
        verify(game).setCroupierCard1(Card.CLUBS_4);
        verify(amountService).withdrawForNewGame(savedGame);
        verify(gameConverter).convert(savedGame);
    }

    @Test
    public void testGameStep() {
        //шаг в игре(сдача карты или играко или курупе)
//        fail("dummy");
    }

    @Test
    public void testGameEnd() {
        //Закрытие игры и провод транзакций
//        fail("dummy");
    }
}
