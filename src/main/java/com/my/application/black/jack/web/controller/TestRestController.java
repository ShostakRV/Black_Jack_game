package com.my.application.black.jack.web.controller;

import com.my.application.black.jack.service.GameService;
import com.my.application.black.jack.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */
@RestController
public class TestRestController {
    private UserService userService;
    private GameService gameService;

    @Inject
    public TestRestController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String helloWorld() {
        return "helloWorld ";
    }

    @RequestMapping(value = "/testDto", method = RequestMethod.GET)
    public SomeDto testDto(@RequestParam(value = "name") String name) {
        return new SomeDto(1, name);
    }


    public static class SomeDto {
        private long id;
        private String name;

        public SomeDto() {
        }

        public SomeDto(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
