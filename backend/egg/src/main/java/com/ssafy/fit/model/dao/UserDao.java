package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.User;

public interface UserDao {

	List<User> selectAll();

	int insertUser(User user);
	
	User selectOne(String id);
	
}