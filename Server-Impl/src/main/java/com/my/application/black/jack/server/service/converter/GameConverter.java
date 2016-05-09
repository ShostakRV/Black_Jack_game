package com.my.application.black.jack.server.service.converter;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.GameState;
import com.my.application.black.jack.model.cards.CardType;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.server.service.dto.CardDto;
import com.my.application.black.jack.server.service.dto.GameDto;
import org.springframework.stereotype.Component;

/**
 * Developer: Roman Shostak
 * Developer: Bohdan Pavlenko
 * Date: 13-Oct-15.
 */
@Component
public class GameConverter {

    public GameDto convert(Game game, int userPoints, int croupierPoints) {
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setRate(game.getRate());
        dto.setGameStatus(game.getState().name());

        for (GameCard card : game.getGameCards()) {
            CardDto cardDto = new CardDto(card.getCardName().toString(), card.getCardMask().toString());
            if (card.getCardType() == CardType.USER) {
                dto.getUserCards().add(cardDto);
            } else {
                if (!game.getState().equals(GameState.ON_PROGRESS)) {
                    dto.getCroupierCards().add(cardDto);
                } else if (dto.getCroupierCards().isEmpty() || game.getFinish() != null) {
                    dto.getCroupierCards().add(cardDto);
                }
            }
        }
        dto.setUserPoints(userPoints);
        dto.setCroupierPoints(croupierPoints);

        return dto;
    }
}
