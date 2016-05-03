package com.my.application.black.jack.server.service;

import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.cards.CardName;
import com.my.application.black.jack.model.cards.CardType;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.server.exception.GameException;
import com.my.application.black.jack.server.service.dto.GameResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created: Pavlenko Bohdan
 * Date: 24.04.2016.
 */
public class GameResultUtils {
    static GameResult getGameResult(List<GameCard> gameCards, boolean stand) {
        GameResult gameResult = new GameResult();
        List<GameCard> userCards = gameCards.stream()
                .filter(card -> card.getCardType() == CardType.USER)
                .collect(Collectors.toList());

        List<GameCard> croupierCards = gameCards.stream()
                .filter(card -> card.getCardType() == CardType.CROUPIER)
                .collect(Collectors.toList());

        if (userCards.isEmpty()) {
            throw new GameException("Something goes wrong! Can not calculate points sum.");
        }

        int userPointsSum = sumCardPoints(userCards);
        int croupierPointsSum = sumCardPoints(croupierCards);
        GameState gameResultState;
        boolean userHasMorePoints = userPointsSum > croupierPointsSum;
        boolean croupierHasMorePoints = userPointsSum < croupierPointsSum;
        if ((stand && userHasMorePoints || (userPointsSum == 21 && userHasMorePoints)) && userPointsSum<=21) {
            if (userCards.size() == 2 && userPointsSum == 21) {
                gameResultState = GameState.USER_BLACK_JACK;
            } else {
                gameResultState = GameState.USER_WIN;
            }
        } else if (stand && croupierHasMorePoints || userPointsSum > 21) {
            if (croupierCards.size() == 2 && croupierPointsSum == 21) {
                gameResultState = GameState.CROUPIER_BLACK_JACK;
            } else {
                gameResultState = GameState.USER_LOSE;
            }
        } else if ((stand && userPointsSum == croupierPointsSum) || userPointsSum == 21) {
            if (userCards.size() == 2 && croupierCards.size() == 2) {
                gameResultState = GameState.DEAD_HEAT;
            } else if (userCards.size() == 2) {
                gameResultState = GameState.USER_BLACK_JACK;
            } else if (stand && croupierCards.size() == 2) {
                gameResultState = GameState.CROUPIER_BLACK_JACK;
            } else {
                gameResultState = GameState.DEAD_HEAT;
            }
        } else {
            gameResultState = GameState.ON_PROGRESS;
        }

        gameResult.setGameState(gameResultState);
        return gameResult;
    }

    public static int sumCardPoints(List<GameCard> gameCards) {
        List<CardName> targetCards = gameCards.stream()
                .map(GameCard::getCardName)
                .collect(Collectors.toList());
        if (targetCards.isEmpty()) {
            throw new GameException("Something goes wrong! Can not calculate points sum.");
        }
        int aceCount = 0;
        int sum = 0;
        for (CardName card : targetCards) {
            if (card.getValue() == 11) {
                aceCount++;
            }
            sum += card.getValue();
            if (sum > 21 && gameCards.size() != 2) {
                while (sum > 21 && aceCount != 0) {
                    sum -= 10;
                    aceCount--;
                }
            }
        }
        return sum;
    }

}
