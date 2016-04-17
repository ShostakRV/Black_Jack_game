package com.my.application.black.jack.server.service.converter;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.model.cards.CardType;
import com.my.application.black.jack.model.cards.GameCard;
import com.my.application.black.jack.server.service.dto.GameDto;
import org.springframework.stereotype.Component;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
@Component
public class GameConverter {

    public GameDto convert(Game game) {
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setRate(game.getRate());
        for (GameCard card : game.getGameCards()) {
            if (card.getCardType() == CardType.USER) {
                dto.getUserCards().add(card.getCard().toString());
            } else {
                if (dto.getCroupierCard().isEmpty()) {
                    dto.getCroupierCard().add(card.getCard().toString());
                } else if (game.getFinish() != null) {
                    dto.getCroupierCard().add(card.getCard().toString());
                }
            }
        }
        return dto;
    }
}
