package com.web.board.service;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.dto.Board;
import com.web.board.model.dto.BoardComment;
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
	
	public Board selectBoardByNo(int no, boolean isRead) {
		Connection conn = JDBCTemplate.getConnection();

		// 조회수만 증가하고 클라이언트가 접속을 못할 경우에 대한 예외 처리도 해야 할 것 같음
		if (boardDao.selectBoardByNo(conn, no) != null && isRead == false) {
			increaseReadCount(conn, no);
		}
		Board board = boardDao.selectBoardByNo(conn, no);
		
		JDBCTemplate.close(conn);
		return board;
	}
	
	private void increaseReadCount(Connection conn, int no) {
		int result = boardDao.updateBoardReadCount(conn, no);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
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

	public int insertBoardComment(BoardComment bc) {
		Connection conn = JDBCTemplate.getConnection();
		int result = boardDao.insertBoardComment(conn, bc);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
}
