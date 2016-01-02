package com.my.application.black.jack.controller;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Developer: Roman Shostak
 * Date: 27-Oct-15.
 */
public class AbstractController {

    public String getUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }



}
