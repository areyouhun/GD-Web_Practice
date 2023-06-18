package com.web.board.service;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.dto.Board;
import com.web.common.JDBCTemplate;

public class BoardService {
	private final BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}

	public List<Board> selectBoard(int currentPage, int numPerPage) {
		Connection conn = JDBCTemplate.getConnection();
		List<Board> boards = boardDao.selectBoard(conn, currentPage, numPerPage);
		JDBCTemplate.close(conn);
		return boards;
	}

	public int selectBoardCount() {
		Connection conn = JDBCTemplate.getConnection();
		int count = boardDao.selectBoardCount(conn);
		JDBCTemplate.close(conn);
		return count;
	}
	
	public Board selectBoardByNo(int no) {
		Connection conn = JDBCTemplate.getConnection();
		Board board = boardDao.selectBoardByNo(conn, no);
		JDBCTemplate.close(conn);
		return board;
	}
	
	public int insertBoard(Board board) {
		Connection conn = JDBCTemplate.getConnection();
		int result = boardDao.insertBoard(conn, board);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
