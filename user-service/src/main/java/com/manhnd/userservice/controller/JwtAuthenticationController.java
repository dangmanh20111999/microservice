package com.manhnd.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manhnd.userservice.model.JwtRequest;
import com.manhnd.userservice.model.JwtResponse;
import com.manhnd.userservice.model.User;
import com.manhnd.userservice.service.UserService;
import com.manhnd.userservice.util.JwtTokenUtil;




@RestController
@RequestMapping("/api/v1/users")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final User user = authenticate(authenticationRequest);
		final String token = JwtTokenUtil.generateToken(user);
		if (token != null) {
			userService.saveTokenWithUserName(user.getUsername(), token);
		}
		
		return ResponseEntity.ok(new JwtResponse(token, user));
	}
	
	
	private User authenticate(JwtRequest request) throws Exception {
		User user = null;
		
		try {
			Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			user = (User)auth.getPrincipal();
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		return user;
	}
}
