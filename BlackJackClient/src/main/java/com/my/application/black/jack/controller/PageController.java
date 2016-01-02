package com.my.application.black.jack.controller;

import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping(value = {"/game/index", "index"} , method = RequestMethod.GET)
    public ModelAndView  index(){
        return new ModelAndView("/index.html", "user", SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
