package com.manhnd.userservice.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class JwtResponse {

	private final String accesssToken;
	private final User user;

	public JwtResponse(String accesssToken, User user) {
		this.accesssToken = accesssToken;
		this.user = user;
	}

	public String getAccessToken() {
		return this.accesssToken;
	}

	public User getUser() {
		return user;
	}	

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
