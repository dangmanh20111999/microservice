package com.manhnd.userservice.util;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import com.auth0.jwt.JWT;
import com.manhnd.userservice.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JwtTokenUtil {

	private static final long JWT_TOKEN_VALIDITY = 24*60;
	public static final String SECRET = "-DKPHCeMooWiW6Gn2cfZpHT5MB-50PSBdUEUAfl_L0G2yywqq-IaCat4B3aNoJL7qz6Ng1kdfvP-QrOVg2ezYg";
	private String token = null;
	
	public JwtTokenUtil(String jwtToken) {
		this.token = jwtToken;
	}
		
	
	public String getUsernameFromToken() {
		return getClaimFromToken(Claims::getSubject);
	}

	
	public Date getExpirationDateFromToken() {
		return getClaimFromToken(Claims::getExpiration);
	}

	
	public <T> T getClaimFromToken(Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken();
		return claimsResolver.apply(claims);
	}
	
	
	public String getClaimFromTokenByName(String name) {
		final Claims claims = getAllClaimsFromToken();
		return (String)claims.get(name);
	}
	
	
	private Claims getAllClaimsFromToken() {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	}

	
	private Boolean isTokenExpired() {
		final Date expiration = getExpirationDateFromToken();
		return expiration.before(new Date());
	}

	
	public static String generateToken(User user) {
		Map<String, Object> claims = addClaims(user); 
		return doGenerateToken(claims, user.getUsername());
	}

	
	private static Map<String, Object> addClaims(User user) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("id", user.getIdstudent());
		claims.put("email", user.getEmail());
		claims.put("phone", user.getPhonenumber());
		claims.put("address", user.getAddress());
		return claims;
	}	

	
	private static String doGenerateToken(Map<String, Object> claims, String subject) {
		
		long now = new Date().getTime();
		long expireTime = now + (JWT_TOKEN_VALIDITY * 1000);
		long issueAtDate = now + (JWT_TOKEN_VALIDITY);
		Date expireDate = new Date(expireTime);
		Date issueAt = new Date(issueAtDate);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(issueAt)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}
	
	
	public Boolean validateToken(UserDetails userDetails) {
		final String username = getUsernameFromToken();
		return (username.equals(userDetails.getUsername()) && !isTokenExpired());
	}
	
	public Boolean validateToken() {
		return (!isTokenExpired());
	}
}
