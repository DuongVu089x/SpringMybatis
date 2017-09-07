package com.dav.mybatis.domain;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 11234213L;

	private Integer id;
	private String username;
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getId() + "," + getUsername() + "," + getPassword();
	}
}
