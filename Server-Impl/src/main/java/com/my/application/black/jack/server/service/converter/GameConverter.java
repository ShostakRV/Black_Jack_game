package com.my.application.black.jack.server.service.converter;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.cards.CardType;
import com.my.application.black.jack.model.cards.CroupierCard;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.model.cards.UserCard;
import com.my.application.black.jack.server.service.GameResultUtils;
import com.my.application.black.jack.server.service.dto.CardDto;
import com.my.application.black.jack.server.service.dto.GameDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Developer: Roman Shostak
 * Developer: Bohdan Pavlenko
 * Date: 13-Oct-15.
 */
@Component
public class GameConverter {

    public GameDto convert(Game game) {
        GameDto dto = new GameDto();
        List<UserCard> userCards = new ArrayList<UserCard>();
        List<CroupierCard> croupierCards = new ArrayList<CroupierCard>();
        dto.setId(game.getId());
        dto.setRate(game.getRate());
        dto.setGameStatus(game.getState().name());

        for (GameCard card : game.getGameCards()) {
            CardDto cardDto = new CardDto(card.getCardName().toString(), card.getCardMask().toString());
            if (card.getCardType() == CardType.USER) {
                dto.getUserCards().add(cardDto);
                userCards.add(new UserCard(card.getCardName(), card.getCardMask()));
            } else {
                if (!game.getState().equals(GameState.ON_PROGRESS)) {
                    dto.getCroupierCards().add(cardDto);
                    croupierCards.add(new CroupierCard(card.getCardName(), card.getCardMask()));
                } else if (dto.getCroupierCards().isEmpty() || game.getFinish() != null) {
                    dto.getCroupierCards().add(cardDto);
                    croupierCards.add(new CroupierCard(card.getCardName(), card.getCardMask()));
                }
            }
        }
        List<GameCard> gameCardsUser = userCards.stream()
                .map(card -> new UserCard(card.getCardName(), card.getCardMask()))
                .collect(Collectors.toList());
        dto.setUserPoints(GameResultUtils.sumCardPoints(gameCardsUser));
        List<GameCard> gameCardsCroupier = croupierCards.stream()
                .map(card -> new CroupierCard(card.getCardName(), card.getCardMask()))
                .collect(Collectors.toList());
        dto.setCroupierPoints(GameResultUtils.sumCardPoints(gameCardsCroupier));

        return dto;
    }
}
