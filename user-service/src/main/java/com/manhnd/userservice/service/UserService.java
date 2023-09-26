package com.manhnd.userservice.service;

public interface UserService {

//	boolean checkUserName(String username);
	void saveTokenWithUserName(String username, String token);
}
