package com.dav.mybatis.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.dav.mybatis.domain.User;

@Mapper
public interface UserMapper {
	User selectByUsername(String username);
}
