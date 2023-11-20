package com.ssafy.fit.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fit.model.dto.Board;
import com.ssafy.fit.model.dto.SearchCondition;

public interface BoardService {

	// 게시글 전체 조회
		List<Board> getList();

		// 게시글 등록
		void writeBoard(Board board);

		// 게시글 상세 조회
		Board getBoard(int id);

		// 게시글 수정
		void modifyBoard(Board board);

		// 게시글 삭제
		void removeBoard(int id);

		// 검색 버튼을 눌렀을 때 처리할 메서드
		List<Board> search(SearchCondition condition);
		
		// 이미지 업로드
		String uploadImage(MultipartFile image);

	    // 이미지 가져오기
	    public String getImage(int id);

}
