package com.dav.mybatis.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dav.mybatis.dao.UserDao;
import com.dav.mybatis.domain.User;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationServiceImpl.
 */
@Service
public class AuthenticationServiceImpl implements UserDetailsService {

	/** The user dao. */
	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userInfo;
		UserDetails userDetails = null;
		try {
			userInfo = userDao.getUserByUsername(username);
			GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getRole());
			userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
					userInfo.getUsername(), userInfo.getPassword(), Arrays.asList(authority));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userDetails;
	}
}
