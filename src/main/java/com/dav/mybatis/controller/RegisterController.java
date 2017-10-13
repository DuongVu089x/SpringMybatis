package com.dav.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dav.mybatis.common.custom.CustomUserValidator;
import com.dav.mybatis.model.User;
import com.dav.mybatis.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class RegisterController {

    /** The user service. */
    @Autowired
    UserService userService;

    /** The user validator. */
    @Autowired
    private CustomUserValidator userValidator;


    /**
     * Login.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    /**
     * Registration.
     *
     * @param userForm the user form
     * @param bindingResult the binding result
     * @param model the model
     * @return the string
     * @throws Exception the exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute(value = "userForm") User userForm, BindingResult bindingResult, Model model) throws Exception {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.save(userForm);
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/login";
    }
}
