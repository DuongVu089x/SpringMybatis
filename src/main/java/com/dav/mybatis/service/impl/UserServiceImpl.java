package com.dav.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dav.mybatis.dao.UserDao;
import com.dav.mybatis.model.User;
import com.dav.mybatis.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see com.dav.mybatis.service.UserService#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) throws Exception {
		return userDao.getUserByUsername(username);
	}

}
