package com.manhnd.userservice.dto;

import lombok.Data;

@Data
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private String employeeId;
	private String token;
	private String refreshtoken;
}
