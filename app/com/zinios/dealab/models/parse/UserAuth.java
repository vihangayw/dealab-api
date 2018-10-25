package com.zinios.dealab.models.parse;

import com.zinios.dealab.models.User;

import java.io.Serializable;

public class UserAuth implements Serializable {

	private User user;
	private String auth;

	public UserAuth() {
	}

	public UserAuth(User user, String auth) {
		this.user = user;
		this.auth = auth;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}
