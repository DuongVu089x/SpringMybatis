package com.dav.mybatis.controller.student;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dav.mybatis.config.TokenHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentController.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	/** The token handler. */
	@Autowired
	private TokenHandler tokenHandler;

	/**
	 * Student.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @return the string
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String student(ModelMap model, Principal principal) {
		if (principal != null && principal.getName() != null && !principal.getName().equals("")) {
			 // Get logged in username
			String name = principal.getName();
			String role = "";
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities();
			for (GrantedAuthority simpleGrantedAuthority : authorities) {
				role += simpleGrantedAuthority.toString();
			}
			String jwtToken = tokenHandler.createTokenForUser(role);
			model.addAttribute("username", name);
			model.addAttribute("jwt", jwtToken);
		}

		return "student";
	}

}
