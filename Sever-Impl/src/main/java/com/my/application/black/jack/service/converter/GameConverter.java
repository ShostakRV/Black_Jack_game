package com.my.application.black.jack.service.converter;

import com.my.application.black.jack.model.Game;
import com.my.application.black.jack.service.dto.GameDto;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public final class GameConverter {
    private GameConverter() {
    }

    public static GameDto convert(Game game) {
        GameDto dto = new GameDto();
        dto.setId(game.getId());
        dto.setRate(game.getRate());
        dto.setUserCard1(game.getUserCard1().toString());
        dto.setUserCard2(game.getUserCard2().toString());
        dto.setCroupierCard1(game.getCroupierCard1().toString());
        return dto;
    }
}
