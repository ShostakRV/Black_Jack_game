package com.my.application.black.jack.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Developer: Roman Shostak
 * Date: 08-Oct-15.
 */
@RestController
public class TestRestController {

    public TestRestController() {

        System.out.print("\n\n\nTest rest controller has been created.\n\n\n");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String helloWorld() {
        return "helloWorld ";
    }
}
