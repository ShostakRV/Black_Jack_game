package com.my.application.black.jack.client.controller;

import com.my.application.black.jack.server.exception.GameException;
import com.my.application.black.jack.server.service.GameService;
import com.my.application.black.jack.server.service.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */

@RestController
@RequestMapping(value = "/game")
public class GameController extends AbstractController {
    private GameService gameService;


    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(value = "/createGame", method = RequestMethod.GET)
    public GameDto createGame(@RequestParam(value = "rate") Long rate) {
        if (rate <= 0) {
            throw new GameException("Rate is incorrect! Rate should be more then 0");
        }
        GameDto gameDto = gameService.createGameForUser(getUserName(), BigDecimal.TEN);
        return gameDto;
    }

    @RequestMapping(value = "/hitCard", method = RequestMethod.GET)
    public GameDto hitCard(@RequestParam(value = "gameId") Long gameId) {
        GameDto gameDto = gameService.hitUserCard(SecurityContextHolder.getContext().getAuthentication().getName(), gameId);
        return gameDto;
    }

    @RequestMapping(value = "/stand", method = RequestMethod.GET)
    public GameDto stand(@RequestParam(value = "gameId") Long gameId) {
        GameDto gameDto = gameService.finishGame(getUserName(), gameId);
        return gameDto;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "HelloWorld! User name: " + SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
