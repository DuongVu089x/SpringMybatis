package com.dav.mybatis.dao;

import com.dav.mybatis.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 * @throws Exception the exception
	 */
	User getUserByUsername(String username) throws Exception;
}
