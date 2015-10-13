package com.my.application.black.jack.service;

import com.my.application.black.jack.service.dto.GameDto;
import com.my.application.black.jack.service.dto.UserDto;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 13-Oct-15.
 */
public interface GameService {
    GameDto createGameForUser(String userEmail, BigDecimal rate);
}
