package com.my.application.black.jack.client.controller;

import com.my.application.black.jack.server.service.GameService;
import com.my.application.black.jack.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */
@RestController
public class TestRestController {

    private UserService userService;
    private GameService gameService;

    @Autowired
    public TestRestController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String helloWorld() {
        return "HelloWorld!!! User name: " + SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @RequestMapping(value = "/testDto", method = RequestMethod.GET)
    public SomeDto testDto(@RequestParam(value = "name", defaultValue = "defaultName") String name) {
        return new SomeDto(1, name, "Some default");
    }

    @RequestMapping(value = "/testDto/{some}", method = RequestMethod.GET)
    public SomeDto testDto2(@RequestParam(value = "name", defaultValue = "Default Name") String name,
                            @PathVariable(value = "some") String some) {
        return new SomeDto(1L, name, some);
    }

    @RequestMapping(value = "/testDto/process", method = RequestMethod.POST)
    public SomeDto testDto2(@RequestBody SomeDto some) {
        some.setName("Processed!");
        some.setSome("Processed!");
        return some;
    }

    public static class SomeDto {
        private long id;
        private String name;
        private String some;

        public SomeDto() {
        }

        public SomeDto(long id, String name, String some) {
            this.id = id;
            this.name = name;
            this.some = some;
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

        public String getSome() {
            return some;
        }

        public void setSome(String some) {
            this.some = some;
        }
    }
}
