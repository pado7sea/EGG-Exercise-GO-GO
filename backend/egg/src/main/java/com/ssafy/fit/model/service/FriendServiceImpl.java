package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.fit.model.dao.FriendDao;
import com.ssafy.fit.model.dto.Friend;
@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	private FriendDao friendDao;
	@Override
	public List<Friend> selectAll() {
		
		return friendDao.selectAll();
	}

	@Override
	public int insertFriend(Friend friend) {
		// TODO Auto-generated method stub
		return friendDao.insertFriend(friend);
	}

	@Override
	public List<Friend> selectOne(String id) {
		return friendDao.selectOne(id);
	}

	
}
