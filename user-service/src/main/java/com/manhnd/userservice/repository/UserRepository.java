package com.manhnd.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manhnd.userservice.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM users where username = ?", nativeQuery = true)
	User findByUsername(String username);

	@Query(value = "SELECT * FROM users WHERE accesstoken = ?", nativeQuery = true)
	User findUserByToken(String accessToken);

	@Query(value = "SELECT * FROM users WHERE refreshtoken = ?", nativeQuery = true)
	User findUserByRefreshToken(String refreshToken);

	@Query(value = "SELECT * FROM users WHERE ids =?", nativeQuery = true)
	User findUserByIds(String ids);

	@Query(value = "UPDATE users SET refreshtoken = NULL WHERE ids =?", nativeQuery = true)
	void deleteRefreshToken(String ids);
	
}
