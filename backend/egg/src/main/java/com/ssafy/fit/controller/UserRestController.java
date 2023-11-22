package com.ssafy.fit.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafy.fit.model.dto.SearchCondition;
import com.ssafy.fit.model.dto.User;
import com.ssafy.fit.model.service.UserService;
<<<<<<< HEAD
import com.ssafy.fit.util.JwtUtil;
=======

import io.swagger.annotations.ApiOperation;


>>>>>>> 8c67857942f2c1e476858cacbe6f098bc9ed3321

@RestController
@RequestMapping("/userapi")
@CrossOrigin("*")
public class UserRestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	// UserService 라고 하는 친구를 주입
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	// 전체유저가져와
	@GetMapping("/users")
	@ApiOperation(value = "유저 조회", notes = "검색조건도 넣으면 같이 가져온다.")
	public ResponseEntity<?> user (SearchCondition condition) {
		List<User> user = userService.search(condition);
		if(user == null || user.size() == 0){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(user, HttpStatus.OK);
	}

	// 회원가입을 해보자 form 태그 형식으로 넘어왔다.
	@PostMapping("signup")
	public ResponseEntity<Integer> signup(@RequestBody User user) {
		// Vue.js에서 비밀번호 해싱
        // Spring Boot에서는 받은 비밀번호를 그대로 저장
        int result = userService.signup(user);
        // result 가 0이면 등록 x, result 가 1이면 등록 o
        return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

//	@PostMapping("login")
//	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
//		System.out.println(123);
//		User tmp = userService.login(user);
//		System.out.println(user);
//		//로그인 실패 (잘못했어)
//		if(tmp == null) {
//			System.out.println("no");
//			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
//			}
//
//		System.out.println("yes");
//		session.setAttribute("loginUser", tmp.getName());
//		return new ResponseEntity<User>(tmp, HttpStatus.OK);
//	}

	// JWT login. 사용자가 입력한 비밀번호를 해싱하여 저장된 해시 값과 비교
	@PostMapping("login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
	    Map<String, Object> result = new HashMap<>();

	    // 클라이언트에서 보내온 비밀번호를 해싱
	    String hashedPassword = user.getPassword(); // 클라이언트에서 이미 해싱된 비밀번호를 전송한다고 가정
	    user.setPassword(hashedPassword);

	    // 서비스에서 사용자 검증을 수행
	    User authenticatedUser = userService.login(user);
	    
	    System.out.println(hashedPassword);

	    if (authenticatedUser != null) {
	        // 로그인 성공: JWT 토큰 생성 및 반환
	        try {
	            String token = jwtUtil.createToken("id", authenticatedUser.getId());
	            result.put("user", authenticatedUser);
	            result.put("access-token", token);
	            result.put("message", "success");
	            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	        } catch (UnsupportedEncodingException e) {
	            result.put("message", "fail");
	            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        // 로그인 실패
	        result.put("message", "fail");
	        return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}



	@GetMapping("logout")
	public ResponseEntity<Void> logout(HttpSession session) {
//		session.removeAttribute("loginUser");
		session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("userapi/{id}")
	public ResponseEntity<User> detail(@PathVariable String id) {
		User user = userService.selectOne(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
<<<<<<< HEAD

=======
	
	
	
	
	
	
	
	
	
	
	
>>>>>>> 8c67857942f2c1e476858cacbe6f098bc9ed3321
}