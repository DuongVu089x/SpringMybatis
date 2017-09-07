package com.dav.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dav.mybatis.domain.User;
import com.dav.mybatis.service.UserService;

@RestController
public class HelloController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public User getAll() {
		return userService.getUserByUsername("duonganhvu");
	}
}
