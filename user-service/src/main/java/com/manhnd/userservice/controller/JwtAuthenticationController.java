package com.manhnd.userservice.controller;

import com.manhnd.userservice.model.*;
import com.manhnd.userservice.request.JwtRequest;
import com.manhnd.userservice.request.TokenRefreshRequest;
import com.manhnd.userservice.response.JwtResponse;
import com.manhnd.userservice.response.TokenRefreshResponse;
import com.manhnd.userservice.service.impl.RefreshTokenServiceImpl;
import com.manhnd.userservice.util.TokenRefreshException;
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

import com.manhnd.userservice.service.UserService;
import com.manhnd.userservice.util.JwtTokenUtil;


@RestController
@RequestMapping("/api/v1/users")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RefreshTokenServiceImpl refreshTokenService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		final User user = authenticate(authenticationRequest);
		final String token = jwtTokenUtil.generateToken(user);
		RefreshToken refreshToken = null;
		if (token != null) {
//			userService.saveTokenWithUserName(user.getUsername(), token);
			refreshToken =refreshTokenService.createRefreshToken(user.getIds());
		}
		
		return ResponseEntity.ok(new JwtResponse(refreshToken.getToken(), token));
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

	@PostMapping(value = "/refreshtoken")
	public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();
		return refreshTokenService.findByToken(requestRefreshToken)
				.map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser)
				.map(user -> {
					String token = jwtTokenUtil.doGenerateRefreshToken(user.getUsername());
					return ResponseEntity.ok(new TokenRefreshResponse(requestRefreshToken,token));
				})
				.orElseThrow(() ->
					new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
	}
}
