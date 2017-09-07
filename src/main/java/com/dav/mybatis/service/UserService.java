package com.dav.mybatis.service;

import com.dav.mybatis.domain.User;

public interface UserService {
	User getUserByUsername(String username);
}
