package com.dav.mybatis.service;

import com.dav.mybatis.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 * @throws Exception the exception
	 */
	User getUserByUsername(String username) throws Exception;


	void save(User user) throws Exception;
}
