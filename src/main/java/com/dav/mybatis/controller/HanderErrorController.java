package com.dav.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// TODO: Auto-generated Javadoc
/**
 * The Class HanderErrorController.
 */
@Controller
public class HanderErrorController {

    /**
     * Not found page.
     *
     * @return the string
     */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String notFoundPage() {
        return "404";
    }

    /**
     * Error page.
     *
     * @return the string
     */
    @RequestMapping(value = "/error-page", method = RequestMethod.GET)
    public String errorPage() {
        return "404";
    }

    /**
     * Access denied.
     *
     * @return the string
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}
