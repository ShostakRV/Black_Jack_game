package com.my.application.black.jack.server.service;

import com.my.application.black.jack.server.service.dto.GameDto;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface GameService {


    GameDto createGameForUser(String userEmail, BigDecimal rate);

    GameDto hitCard(String requestedUser, long gameId);

    GameDto finishGame(String requestedUser, long gameId);
}
