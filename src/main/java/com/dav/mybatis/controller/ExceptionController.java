package com.dav.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {

//    @ExceptionHandler(Exception.class)
//    public String handleError(HttpServletRequest request, Exception e)   {
//        return "redirect:/error-page";
//    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, Exception e)   {
        return "redirect:/404";
    }
}