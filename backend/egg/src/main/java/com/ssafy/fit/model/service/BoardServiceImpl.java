package com.ssafy.fit.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public void uploadImage(int id, String imgUrl) {
		boardDao.uploadImage(id, imgUrl);
	}

	@Override
	public String getImage(int id) {
		// TODO Auto-generated method stub
		return boardDao.getImage(id);
	}

	

	
	
}
