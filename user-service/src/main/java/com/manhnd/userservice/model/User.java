package com.manhnd.userservice.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table (name = "users")
@Entity
@Data
public class User implements UserDetails {

	private static final long serialVersionUID = 3592549577903104696L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String ids;
	private String firstname;
	private String lastname;
	private String email;
	private int phonenumber;
	private String authoritynames;
	private String username;
	private String address;
//	private String refreshtoken;

	@JsonIgnore
	private String password;	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAuthoritynames() {
		return authoritynames;
	}
	public void setAuthoritynames(String authoritynames) {
		this.authoritynames = authoritynames;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<String> authority = new ArrayList<>();
		authority.add(authoritynames);
		List<GrantedAuthority> list = authority
				.stream()
				.map(auth -> new SimpleGrantedAuthority(auth))
				.collect(Collectors.toList());

		return list;
	}
	
}

