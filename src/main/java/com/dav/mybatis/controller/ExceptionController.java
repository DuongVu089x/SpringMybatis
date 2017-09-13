package com.dav.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionController.
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Handle error 404.
     *
     * @param request the request
     * @param e the e
     * @return the string
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, Exception e)   {
        return "redirect:/404";
    }
}