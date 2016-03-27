package com.my.application.black.jack.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Creator: Shostak Roman
 * Date: 02.01.2016.
 */
@Controller
public class PageController {

    @RequestMapping(value = {"/public/login", "/"}, method = RequestMethod.GET)
    public String index() {

        return "index";
    }

    @RequestMapping(value = {"/index2"}, method = RequestMethod.GET)
    public ModelAndView index2() {

        return new ModelAndView("index");
    }
}
