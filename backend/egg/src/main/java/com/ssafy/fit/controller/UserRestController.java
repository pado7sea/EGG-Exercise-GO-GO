package com.ssafy.fit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.UserService;



@RestController
@RequestMapping("/userapi")
@CrossOrigin("*")
public class UserRestController {
	
	//UserService 라고 하는 친구를 주입
	@Autowired
	private UserService userService;
	
	//전체유저가져와
	@GetMapping("/users")
	public ResponseEntity<List<User>> userList() {
		List<User> user = userService.getUserList();
		return new ResponseEntity<List<User>>(user, HttpStatus.CREATED);
	}
	
	//회원가입을 해보자 form 태그 형식으로 넘어왔다.
	@PostMapping("signup")
	public ResponseEntity<Integer> signup(@RequestBody User user) {
		int result = userService.signup(user);
		
		//result 가 0이면 등록 x
		//result 가 1이면 등록 o
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}
	
	
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		System.out.println(123);
		User tmp = userService.login(user);
		System.out.println(user);
		//로그인 실패 (잘못했어)
		if(tmp == null) {
			System.out.println("no");
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}

		System.out.println("yes");
		session.setAttribute("loginUser", tmp.getName());
		return new ResponseEntity<User>(tmp, HttpStatus.OK);
	}
	
	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
//		session.removeAttribute("loginUser");
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("userapi/{id}")
	public ResponseEntity<User> detail(@PathVariable String id){
		User user = userService.selectOne(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}