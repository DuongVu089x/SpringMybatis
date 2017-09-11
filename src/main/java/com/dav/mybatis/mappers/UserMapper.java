package com.dav.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.dav.mybatis.model.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserMapper.
 */
@Mapper
public interface UserMapper {

	/**
	 * Select by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User selectByUsername(String username);
}
