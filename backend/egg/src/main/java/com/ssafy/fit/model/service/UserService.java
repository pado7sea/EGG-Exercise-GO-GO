package com.ssafy.fit.model.service;

import java.util.List;

import com.ssafy.fit.model.dto.SearchCondition;
import com.ssafy.fit.model.dto.User;

public interface UserService {

	List<User> selectAll();

	int signup(User user);

	User login(User user);
	
	User selectOne(String id);
	
	List<User> search(SearchCondition condition);
}