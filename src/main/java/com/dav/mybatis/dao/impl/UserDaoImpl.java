package com.dav.mybatis.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dav.mybatis.dao.UserDao;
import com.dav.mybatis.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDaoImpl.
 */
@Repository
public class UserDaoImpl implements UserDao {

	/** The sql session factory. */
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/* (non-Javadoc)
	 * @see com.dav.mybatis.dao.UserDao#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) throws Exception {
		SqlSession sqlSession = null;
		User user = null;
		try {
			sqlSession = sqlSessionFactory.openSession();
			user = sqlSession.selectOne("selectByUsername", username);
		} catch (Exception e) {
			throw e;
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return user;
	}

}
