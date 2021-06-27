package com.example.demo.framework.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.framework.model.UserMaster;

@Repository
public class UserMasterRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void saveUser(UserMaster userMaster) {
		String sql="insert into user_master(username,password,role) values('"+userMaster.getUsername()
		+"','"+userMaster.getPassword()+"','"+userMaster.getRole()+"')";
		int row=jdbcTemplate.update(sql);
		System.out.println("Row Updated :"+row);
		
	}
	
	public UserMaster getUser(String username) {
		String sql="select username,password,role from user_master where username='"+username+"';";
		List<UserMaster> userMaster=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(UserMaster.class));
		return userMaster.get(0);
	}
}
