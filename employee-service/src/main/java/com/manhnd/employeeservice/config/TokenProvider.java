package com.manhnd.employeeservice.config;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {

    private String jwtSecret = "-DKPHCeMooWiW6Gn2cfZpHT5MB-50PSBdUEUAfl_L0G2yywqq-IaCat4B3aNoJL7qz6Ng1kdfvP-QrOVg2ezYg";
//
//    private long jwtExpirationMs = 24*60;
//    private static final String AUTHORITIES_KEY = "auth";

//    public String generateToken(Authentication authentication) {
//        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
//
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);
//
//        return Jwts.builder()
//                .setSubject(userPrincipal.getName())
//                .setIssuedAt(new Date())
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }
//
//    public Long getUserIdFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return Long.parseLong(claims.getSubject());
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            // Invalid JWT signature
//        } catch (MalformedJwtException ex) {
//            // Invalid JWT token
//        } catch (ExpiredJwtException ex) {
//            // Expired JWT token
//        } catch (UnsupportedJwtException ex) {
//            // Unsupported JWT token
//        } catch (IllegalArgumentException ex) {
//            // JWT claims string is empty
//        }
//        return false;
//    }

    public Authentication getAuthentication(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new BadCredentialsException("Invalid token");
        }

        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        
        List<String> authority = new ArrayList<>();
		authority.add(claims.get("role").toString());
		List<GrantedAuthority> list = authority
				.stream()
				.map(auth -> new SimpleGrantedAuthority(auth))
				.collect(Collectors.toList());

        
        User principal = new User(claims.getSubject(), "", list);

        return new UsernamePasswordAuthenticationToken(principal, token, list);

    }

}

