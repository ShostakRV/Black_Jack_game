package com.my.application.black.jack.controller;

import com.my.application.black.jack.service.GameService;
import com.my.application.black.jack.service.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */

@RestController
@RequestMapping(value = "game")
public class GameController extends AbstractController {
    private GameService gameService;


    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/createGame", method = RequestMethod.GET)
    public GameDto createGame() {
        GameDto gameDto = gameService.createGameForUser(getUserName(), BigDecimal.TEN);
        return gameDto;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "HelloWorld! User name: " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
