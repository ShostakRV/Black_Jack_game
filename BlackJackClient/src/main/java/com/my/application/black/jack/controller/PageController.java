package com.my.application.black.jack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    //    @RequestMapping(value = {"/WEB-INF/index.jsp"} , method = RequestMethod.GET)
    public String index2() {

        return "index";
    }
}
