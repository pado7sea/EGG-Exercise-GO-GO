package com.ssafy.fit.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fit.model.dao.BoardDao;
import com.ssafy.fit.model.dto.Board;
import com.ssafy.fit.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao;

	@Autowired
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	@Override
	public List<Board> getList() {
		System.out.println("모든 게시글을 가지고 왔습니다.");
		return boardDao.selectAll();
	}

	@Transactional
	@Override
	public void writeBoard(Board board) {
		System.out.println("게시글을 작성합니다.");
		// 강제로 ID를 99번으로 결정하겠다.
//		board.setId(99);
		boardDao.insertBoard(board);
//		boardDao.insertBoard(board);
	}

	@Override
	public Board getBoard(int id) {
		System.out.println(id + "번 글을 읽었습니다.");
		boardDao.updateViewCnt(id);
		return boardDao.selectOne(id);
	}

	// 서6 최영진 훌륭 그자체
	@Transactional
	@Override
	public void modifyBoard(Board board) {
		boardDao.updateBoard(board);
	}

	@Transactional
	@Override
	public void removeBoard(int id) {
		System.out.println(id + "번 글을 삭제 했습니다.");
		boardDao.deleteBoard(id);
	}

	@Override
	public List<Board> search(SearchCondition condition) {
		return boardDao.search(condition);
	}

	@Override
    public String uploadImage(MultipartFile image) {
        // 이미지를 서버에 업로드하고 파일 경로를 반환하는 로직 구현
        // 예제에서는 단순히 파일 경로를 문자열로 반환하도록 했습니다.
        // 실제로는 업로드된 이미지를 저장하고 저장된 경로를 반환하도록 구현해야 합니다.
        String imagePath = "C:/board_images";
        String imageName = "generated_image_name.jpg"; // 이미지 파일명 생성 로직 필요
        return imagePath + imageName;
    }

	@Override
	public String getImage(int id) {
		// TODO Auto-generated method stub
		return boardDao.getImage(id);
	}

	
	

	
	
}
