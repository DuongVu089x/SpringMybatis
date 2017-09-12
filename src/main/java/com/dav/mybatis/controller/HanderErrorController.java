package com.dav.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HanderErrorController {

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage() {
        return "404";
    }

    @RequestMapping(value = "/error-page", method = RequestMethod.GET)
    public String errorPage() {
        return "404";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}
