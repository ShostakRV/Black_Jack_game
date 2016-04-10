package com.my.application.black.jack.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Creator: Shostak Roman
 * Date: 02.01.2016.
 */
@Controller
public class PageController {

	@RequestMapping( value = {"/public/login"}, method = RequestMethod.GET )
	public String index() {

		return "home";
	}

	@RequestMapping( value = {"/myTemplate"}, method = RequestMethod.GET )
	public ModelAndView index2() {
		return new ModelAndView( "myTemplate" );
	}

	@RequestMapping( value = {"/userInfo"}, method = RequestMethod.GET )
	public ModelAndView testUserInfo() {
		return new ModelAndView( "userInfo" );
	}

	@RequestMapping( value = {"/games/blackJackGame"}, method = RequestMethod.GET )
	public ModelAndView blackJackGame() {
		return new ModelAndView( "blackJackGame" );
	}
}
