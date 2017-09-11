package com.dav.mybatis.controller;

import org.springframework.stereotype.Controller;

// TODO: Auto-generated Javadoc
/**
 * The Class AccessDeniedController.
 */
@Controller
public class AccessDeniedController {

    /**
     * Access denied.
     *
     * @return the string
     */
    public String accessDenied() {
        return "403";
    }
}
