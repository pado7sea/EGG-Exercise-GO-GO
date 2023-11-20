package com.ssafy.fit.model.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fit.model.dto.Board;
import com.ssafy.fit.model.dto.SearchCondition;

public interface BoardDao {
	public List<Board> selectAll();

	// ID에 해당하는 게시글 하나 가져오기
	public Board selectOne(int id);

	// 게시글 등록
	public void insertBoard(Board board);

	// 게시글 삭제
	public void deleteBoard(int id);

	// 게시글 수정
	public void updateBoard(Board board);

	// 조회수 증가
	public void updateViewCnt(int id);

	// 검색 기능
	public List<Board> search(SearchCondition condition);
	
	// 이미지 업로드
	String uploadImage(MultipartFile image);


    // 이미지 가져오기
    public String getImage(int id);
    
}
