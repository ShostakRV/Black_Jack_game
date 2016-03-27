package com.my.application.black.jack.client.controller;

import com.my.application.black.jack.server.service.GameService;
import com.my.application.black.jack.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */
@RestController
public class AuntificationTest {
    private UserService userService;
    private GameService gameService;

    @Autowired
    public AuntificationTest(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @ResponseBody
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String helloWorld() {
        return "HelloWorld! User name: ";
    }
}
