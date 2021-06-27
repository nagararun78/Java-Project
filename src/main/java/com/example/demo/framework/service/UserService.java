package com.example.demo.framework.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.framework.dao.UserMasterRepository;
import com.example.demo.framework.model.UserMaster;

@Service
public class UserService {

	@Autowired
	PasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserMasterRepository userMasterRepository;
	
	private HashMap<String,UserMaster> users=new HashMap<>();
		
	public UserMaster loadUserByUsernameFromMemory(String username) {
		return users.get(username);
	}
	
	public void saveUserToMemory(String username,String password,String role) {
		UserMaster user = new UserMaster();
		user.setUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setRole(role);
		
		users.put(username, user);
	}
	
	public UserMaster loadUserByUsernameFromDatabase(String username) {
		return userMasterRepository.getUser(username);
	}
	
	public void saveUserToDatabase(String username,String password,String role) {
		UserMaster user = new UserMaster();
		user.setUsername(username);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setRole(role);
		
		userMasterRepository.saveUser(user);
	}
}
