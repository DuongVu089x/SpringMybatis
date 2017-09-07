package com.dav.mybatis.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dav.mybatis.domain.User;
import com.dav.mybatis.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("selectByUsername", username);
	}

}
