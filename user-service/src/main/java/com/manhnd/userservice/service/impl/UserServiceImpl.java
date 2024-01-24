package com.manhnd.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manhnd.userservice.model.User;
import com.manhnd.userservice.repository.UserRepository;
import com.manhnd.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

//	@Override
//	public boolean checkUserName(String username) {
//		User _user = userRepo.findByUsername(username);
//		if (_user != null) {
//			return true;
//		}
//		return false;
//	}

//	@Override
//	public void saveTokenWithUserName(String username, String token) {
//		User _user = userRepo.findByUsername(username);
//		_user.setAccesstoken(token);
//		userRepo.save(_user);
//	}

}
