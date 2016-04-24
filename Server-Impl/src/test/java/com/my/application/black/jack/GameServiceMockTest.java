package com.my.application.black.jack;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.User;
import com.my.application.black.jack.model.cards.CardType;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.dao.GameRepository;
import com.my.application.black.jack.server.dao.UserRepository;
import com.my.application.black.jack.server.exception.GameException;
import com.my.application.black.jack.server.service.AmountService;
import com.my.application.black.jack.server.service.CardGenerator;
import com.my.application.black.jack.server.service.GameCardService;
import com.my.application.black.jack.server.service.GameServiceImpl;
import com.my.application.black.jack.server.service.converter.GameConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
@RunWith(MockitoJUnitRunner.class)
public class GameServiceMockTest {

    private static final String USER_NAME = "test@email.com";
    private static final long GAME_ID = 777L;
    private Set<GameCard> generatedCards = new HashSet<>();
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
    @Mock
    private GameCardService gameCardService;

    private UserCard userCard1 = new UserCard(Card.CLUBS_ACE);

    private UserCard userCard2 = new UserCard(Card.CLUBS_6);

    private CroupierCard croupierCard1 = new CroupierCard(Card.CLUBS_2);

    private CroupierCard croupierCard2 = new CroupierCard(Card.CLUBS_3);

    private List<GameCard> gameCards = new ArrayList<>();

    @Before
    public void init() {
        when(gameRepository.saveAndFlush(game)).thenReturn(savedGame);

        when(gameRepository.newEntity()).thenReturn(game);

        when(savedGame.getGameCards()).thenReturn(gameCards);

        when(gameCardService.createCardGenerator(game)).thenReturn(generator);
        when(userRepository.findByEmail(USER_NAME)).thenReturn(user);

        when(user.getAmount()).thenReturn(new BigDecimal(5000));

        when(game.getGameCards()).thenReturn(gameCards);

        when(game.getUser()).thenReturn(user);

        when(gameRepository.findOne(GAME_ID)).thenReturn(game);

        when(generator.nextUserCard()).then(invocation -> {
            if (generatedCards.size() == 0) {
                generatedCards.add(userCard1);
                return userCard1;
            } else if (generatedCards.size() == 2) {
                generatedCards.add(userCard2);
                return userCard2;
            } else {
                throw new RuntimeException("unexpected call");
            }
        });
        when(generator.nextCroupierCard()).then(invocation -> {
            if (generatedCards.size() == 1) {
                generatedCards.add(croupierCard1);
                return croupierCard1;
            } else if (generatedCards.size() == 3) {
                generatedCards.add(croupierCard2);
                return croupierCard2;
            } else {
                throw new RuntimeException("unexpected call");
            }
        });
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
        verify(game).setState(GameState.ON_PROGRESS);

        assertEquals(4, gameCards.size());
        assertEquals(userCard1, gameCards.get(0));
        assertEquals(userCard2, gameCards.get(1));
        assertEquals(croupierCard1, gameCards.get(2));
        assertEquals(croupierCard2, gameCards.get(3));

        verify(amountService).withdrawForNewGame(savedGame);
        verify(gameConverter).convert(savedGame);
    }

    @Test(expected = GameException.class)
    public void testGameCreationWithWrongAmount() {
        BigDecimal rate = new BigDecimal(-100);
        gameService.createGameForUser(USER_NAME, rate);
    }

    @Test
    public void testGameStep() {

        gameService.hitUserCard(USER_NAME, GAME_ID);
        assertEquals(1, gameCards.size());
        assertEquals(userCard1, gameCards.get(0));
        verify(gameRepository).saveAndFlush(game);
        verify(gameConverter).convert(savedGame);
    }

    @Test
    public void testGameEnd() {
        //Закрытие игры и провод транзакций
//        fail("dummy");
    }

    @Test
    public void testSumCardPointsForeAces() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        gameCards.add(new UserCard(Card.DIAMONDS_ACE));
        gameCards.add(new UserCard(Card.DIAMONDS_ACE));
        gameCards.add(new UserCard(Card.DIAMONDS_ACE));
        gameCards.add(new UserCard(Card.DIAMONDS_ACE));
        Method sumCardPoints = GameServiceImpl.class.getDeclaredMethod("sumCardPoints", List.class, CardType.class);
        sumCardPoints.setAccessible(true);
        int res = (int) sumCardPoints.invoke(gameService, gameCards, CardType.USER);
        assertEquals((11 + 3), res);
    }

    @Test
    public void testSumCardPointsTwoAces() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        gameCards.add(new UserCard(Card.DIAMONDS_ACE));
        gameCards.add(new UserCard(Card.DIAMONDS_ACE));
        Method sumCardPoints = GameServiceImpl.class.getDeclaredMethod("sumCardPoints", List.class, CardType.class);
        sumCardPoints.setAccessible(true);
        int res = (int) sumCardPoints.invoke(gameService, gameCards, CardType.USER);
        assertEquals((22), res);
    }

    @Test
    public void testSumCardPointsTest3() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        gameCards.add(new UserCard(Card.DIAMONDS_2));
        gameCards.add(new UserCard(Card.DIAMONDS_2));
        gameCards.add(new UserCard(Card.DIAMONDS_2));
        gameCards.add(new UserCard(Card.DIAMONDS_2));
        gameCards.add(new UserCard(Card.DIAMONDS_3));
        gameCards.add(new UserCard(Card.DIAMONDS_3));
        gameCards.add(new UserCard(Card.DIAMONDS_3));
        gameCards.add(new UserCard(Card.DIAMONDS_3));
        gameCards.add(new UserCard(Card.DIAMONDS_4));
        gameCards.add(new UserCard(Card.DIAMONDS_4));
        gameCards.add(new UserCard(Card.DIAMONDS_4));
        gameCards.add(new UserCard(Card.DIAMONDS_4));
        Method sumCardPoints = GameServiceImpl.class.getDeclaredMethod("sumCardPoints", List.class, CardType.class);
        sumCardPoints.setAccessible(true);
        int res = (int) sumCardPoints.invoke(gameService, gameCards, CardType.USER);
        assertEquals((2 * 4 + 3 * 4 + 4 * 4), res);
        System.out.println(res);
    }
}
