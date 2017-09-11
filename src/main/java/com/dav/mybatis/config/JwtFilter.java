package com.dav.mybatis.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.dav.mybatis.common.util.Constants;
import com.dav.mybatis.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class JwtFilter.
 */
public class JwtFilter extends GenericFilterBean {

	/** The token handler. */
	@Autowired
	private TokenHandler tokenHandler;

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The list user. */
	List<HashMap<String, String>> listUser = new ArrayList<HashMap<String, String>>();

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		createService(req);

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader(Constants.STR_HEADER_AUTHORIZATION);
		String url = request.getRequestURI();

		if (authHeader == null) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constants.STR_UNAUTHORIZED);
			return;
		}
		String role = tokenHandler.parseUserFromToken(authHeader);

		if (role.equals("USER") && !url.contains("get")) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, Constants.STR_FORBIDDEN);
			return;
		}
		chain.doFilter(req, res);

	}

	/**
	 * Creates the service.
	 *
	 * @param request the request
	 */
	private void createService(ServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		if (tokenHandler == null) {
			tokenHandler = webApplicationContext.getBean(TokenHandler.class);
		}
		if (userService == null) {
			userService = webApplicationContext.getBean(UserService.class);
		}
	}
}
