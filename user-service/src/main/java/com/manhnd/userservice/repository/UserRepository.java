package com.manhnd.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.manhnd.userservice.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value = "SELECT * FROM users where username = ?", nativeQuery = true)
	User findByUsername(String username);

}
