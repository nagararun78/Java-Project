package com.example.demo.framework.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.framework.model.UserMaster;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Get User details from memory
		UserMaster userMaster=userService.loadUserByUsernameFromDatabase(username);
		
		UserDetails user=null;
		if(userMaster !=null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userMaster.getRole());
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(authority);
			user = new User(userMaster.getUsername(), userMaster.getPassword(), authorities);
			//user = new User(userMaster.getUsername(), "$2a$10$6CW1agMzVzBhxDzK0PcxrO/cQcmN9h8ZriVEPy.6DJbVeyATG5mWe", new ArrayList<>());
		}
		return user;
	}

}

