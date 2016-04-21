package com.my.application.black.jack;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.User;
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
import com.my.application.black.jack.server.service.dto.GameDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Mock
    private UserCard userCard1;
    @Mock
    private UserCard userCard2;
    @Mock
    private CroupierCard croupierCard1;
    @Mock
    private CroupierCard croupierCard2;
    @Mock
    private List<GameCard> gameCards;

    @Before
    public void init() {
        when(gameRepository.saveAndFlush(game)).thenReturn(savedGame);
        when(gameRepository.newEntity()).thenReturn(game);

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

        verify(gameCards).add(userCard1);
        verify(gameCards).add(userCard2);
        verify(gameCards).add(croupierCard1);
        verify(gameCards).add(croupierCard2);

        verify(amountService).withdrawForNewGame(savedGame);
        verify(gameConverter).convert(savedGame);
    }

    @Test(expected = GameException.class)
    public void testGameCreationWithWrongAmount() {
        BigDecimal rate = new BigDecimal(-100);
        gameService.createGameForUser(USER_NAME, rate);
    }

    /*
    Hit card
    Проверка выдчи карты
     */
    @Test
    public void testGameStep() {
        BigDecimal rate = new BigDecimal(100);
        GameDto gameForUser = gameService.createGameForUser(USER_NAME, rate);
        verify(game).setUser(user);
        verify(game).setRate(rate);
        verify(gameCards).add(userCard1);
        verify(gameCards).add(userCard2);
        verify(gameCards).add(croupierCard1);
        verify(gameCards).add(croupierCard2);

        verify(gameCards).add(generator.nextUserCard());

        //GameDto gameDto = gameService.hitUserCard(USER_NAME, 777L);

    }

    @Test
    public void testGameEnd() {
        //Закрытие игры и провод транзакций
//        fail("dummy");
    }
}
