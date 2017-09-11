package com.dav.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dav.mybatis.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class LoginController {

    /** The user service. */
    @Autowired
    UserService userService;

    /**
     * Login.
     *
     * @return the string
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
