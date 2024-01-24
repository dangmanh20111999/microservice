package com.manhnd.userservice.response;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
@Data
public class JwtResponse {

	private final String accesssToken;
	private String refreshToken;

	public JwtResponse(String refreshToken, String accesssToken) {
		this.refreshToken = refreshToken;
		this.accesssToken = accesssToken;
	}


	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
