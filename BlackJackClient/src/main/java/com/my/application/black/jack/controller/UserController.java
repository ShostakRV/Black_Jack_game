package com.my.application.black.jack.controller;

import com.my.application.black.jack.model.User;
import com.my.application.black.jack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Developer: Roman Shostak
 * Date: 12-Oct-15.
 */
@RestController()
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userGG", method = RequestMethod.POST)
    public User createUser(@RequestBody @Valid final User user) {
        LOGGER.debug("Received request to create the {}", user);
        return userService.save(user);
    }


    @RequestMapping(value = "/testUser", method = RequestMethod.POST)
    public User testUser() {
        User u = new User();
        u.setEmail("hello@ggg.com");
        return u;
    }
}
