package com.ssafy.fit.model.dao;

import java.util.List;

import com.ssafy.fit.model.dto.Friend;

public interface FriendDao {
	List<Friend> selectAll();

	int insertFriend(Friend friend);
	
	List<Friend> selectOne(String id);
	
	int deleteFriend(Friend friend);
}
