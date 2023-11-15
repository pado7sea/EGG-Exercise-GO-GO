package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.FriendDao;
import com.ssafy.fit.model.dto.Friend;


public interface FriendService {	
	
	List<Friend> selectAll();

	int insertFriend(Friend friend);
	
	List<Friend> selectOne(String id);

}
