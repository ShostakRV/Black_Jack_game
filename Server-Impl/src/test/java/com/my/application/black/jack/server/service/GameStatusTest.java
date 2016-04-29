package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.Card;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created: Pavlenko Bohdan
 * Date: 24.04.2016.
 */
public class GameStatusTest {

    private List<GameCard> gameCardList;
    private UserCard u11 = new UserCard(Card.CLUBS_ACE);
    private UserCard u10 = new UserCard(Card.CLUBS_10);
    private UserCard u9 = new UserCard(Card.CLUBS_9);
    private UserCard u8 = new UserCard(Card.CLUBS_8);
    private UserCard u2 = new UserCard(Card.CLUBS_2);
    private CroupierCard c11 = new CroupierCard(Card.CLUBS_ACE);
    private CroupierCard c10 = new CroupierCard(Card.CLUBS_10);
    private CroupierCard c8 = new CroupierCard(Card.CLUBS_8);
    private CroupierCard c2 = new CroupierCard(Card.CLUBS_2);
    private boolean stand;

    @Before
    public void init() {
        stand = false;
        gameCardList = new ArrayList<>();
    }

    @Test
    public void testUserBlackJackTwoCards() {
        gameCardList.add(u11);
        gameCardList.add(u10);
        gameCardList.add(c10);
        gameCardList.add(c10);
        assertEquals(GameState.USER_BLACK_JACK, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testUserWin() {
        gameCardList.add(u2);
        gameCardList.add(u10);
        gameCardList.add(u9);
        gameCardList.add(c2);
        gameCardList.add(c2);
        assertEquals(GameState.USER_WIN, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testCroupierBlackJackTwoCardsAndStand() {
        stand = true;
        gameCardList.add(u2);
        gameCardList.add(u10);
        gameCardList.add(c10);
        gameCardList.add(c11);
        assertEquals(GameState.CROUPIER_BLACK_JACK, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testUserLose() {
        stand = true;
        gameCardList.add(u2);
        gameCardList.add(u10);
        gameCardList.add(c2);
        gameCardList.add(c8);
        gameCardList.add(c11);
        assertEquals(GameState.USER_LOSE, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testDeadHeatTwoCards() {
        gameCardList.add(u10);
        gameCardList.add(u11);
        gameCardList.add(c10);
        gameCardList.add(c11);
        assertEquals(GameState.DEAD_HEAT, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testUserBlackJackCroupier21MoreThenTwoCards() {
        gameCardList.add(u10);
        gameCardList.add(u11);
        gameCardList.add(c2);
        gameCardList.add(c8);
        gameCardList.add(c11);
        assertEquals(GameState.USER_BLACK_JACK, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testCroupierBlackJackUser21MoreThenTwoCards() {
        stand = true;
        gameCardList.add(u2);
        gameCardList.add(u8);
        gameCardList.add(u11);
        gameCardList.add(c10);
        gameCardList.add(c11);
        assertEquals(GameState.CROUPIER_BLACK_JACK, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testDeadHeatMoreThenTwoCards() {
        gameCardList.add(u2);
        gameCardList.add(u8);
        gameCardList.add(u11);
        gameCardList.add(c10);
        gameCardList.add(c11);
        assertEquals(GameState.DEAD_HEAT, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testOnProgress() {
        gameCardList.add(u2);
        gameCardList.add(u11);
        gameCardList.add(c10);
        gameCardList.add(c2);
        assertEquals(GameState.ON_PROGRESS, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }

    @Test
    public void testCroupierBlackJackUserTwoCards() {
        gameCardList.addAll(Collections.singletonList(u2));
        gameCardList.add(u11);
        gameCardList.add(c10);
        gameCardList.add(c11);
        assertEquals(GameState.ON_PROGRESS, GameResultUtils.getGameResult(gameCardList, stand).getGameState());
    }
    // todo + 2 test should be!
}
