package com.ssafy.fit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.Friend;
import com.ssafy.fit.model.service.FriendService;

@RestController
@RequestMapping("/friendapi")
@CrossOrigin("*")
public class FriendRestController {
	
	//UserService 라고 하는 친구를 주입
	@Autowired
	private FriendService friendService;
	
	//전체유저가져와
	@GetMapping("/friends")
	public ResponseEntity<List<Friend>> friendList() {
		List<Friend> friend = friendService.selectAll();
		return new ResponseEntity<List<Friend>>(friend, HttpStatus.CREATED);
	}
	
	//회원가입을 해보자 form 태그 형식으로 넘어왔다.
	@PostMapping("insert")
	public ResponseEntity<Integer> insert(@RequestBody Friend friend) {
		int result = friendService.insertFriend(friend);
		
		//result 가 0이면 등록 x
		//result 가 1이면 등록 o
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/friends/{id}")
	public ResponseEntity<?> selectOne(@PathVariable String id) {
//		System.out.println(123);
		List<Friend> friend = friendService.selectOne(id);
		return new ResponseEntity<List<Friend>>(friend, HttpStatus.OK);
	}
	
	@DeleteMapping("/friends/{user_id}/{friend_id}")
	public ResponseEntity<Void> deleteFriend(
	        @PathVariable("user_id") String user_id,
	        @PathVariable("friend_id") String friend_id) {

	   
		Friend friend = new Friend();
		friend.setUser_id(user_id);
		friend.setFriend_id(friend_id);
	    // 삭제를 시도
	    int isDeleted = friendService.deleteFriend(friend);

	    if (isDeleted == 1) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        // 삭제 실패 시
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 또는 다른 적절한 상태 코드 사용
	    }
	}
	
	
	
	
	
	
	
	
	
	
}