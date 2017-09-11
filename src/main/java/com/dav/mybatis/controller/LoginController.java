package com.dav.mybatis.controller;

import java.security.Principal;

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
    @RequestMapping({"/login","/"})
    public String login(Principal principal) {
        if (principal != null && principal.getName() != null && !principal.getName().equals("")) {
            return "redirect:/student/";
        }
        return "login";
    }

}
