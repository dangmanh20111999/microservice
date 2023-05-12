package com.manhnd.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.manhnd.userservice.model.User;
import com.manhnd.userservice.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		User users = new User();
		if (user == null) {
			return null;
		}
		else {
				users.setIdstudent(user.getIdstudent());
				users.setFirstname(user.getFirstname());
				users.setLastname(user.getLastname());
				users.setEmail(user.getEmail());
				users.setAuthoritynames(user.getAuthoritynames());
				users.setPhonenumber(user.getPhonenumber());
				users.setUsername(user.getUsername());
				users.setAddress(user.getAddress());
				users.setPassword(encoder.encode(user.getPassword()));
		}
		
		return users;
	}

}
