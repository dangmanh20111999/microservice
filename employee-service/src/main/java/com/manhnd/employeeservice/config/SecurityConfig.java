package com.manhnd.employeeservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf().disable()
			.authorizeRequests() 
			.antMatchers("/api/v1/employees/**").access("hasAnyAuthority('JWT_USER')")
//			.antMatchers(HttpMethod.GET, "/contact/**").access("hasAnyAuthority('JWT_USER','JWT_ADMIN')")
//			.antMatchers("/admin/**").access("hasAuthority('JWT_ADMIN')")
			.anyRequest().authenticated().and()
			.httpBasic();
	}
}
