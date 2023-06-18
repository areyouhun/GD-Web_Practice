package com.web.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.board.model.dto.Board;
import com.web.common.PropertiesGenerator;
import com.web.member.service.MemberService;

public class BoardDao {
	private static final String SQL_PATH = "/sql/board/board_sql.properties";

	private final String path = BoardDao.class.getResource(SQL_PATH).getPath();
	private final Properties sql;

	public BoardDao() {
		sql = PropertiesGenerator.by(path);
	}

	public List<Board> selectBoard(Connection conn, int currentPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boards = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoard"));
			pstmt.setInt(1, (currentPage * numPerPage) - numPerPage + 1);
			pstmt.setInt(2, currentPage * numPerPage);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				boards.add(getBoardBy(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boards;
	}

	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardCount"));
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Board selectBoardByNo(Connection conn, int no) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertBoard(Connection conn, Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	private Board getBoardBy(ResultSet rs) throws SQLException {
		return Board.builder().boardNo(rs.getInt("BOARD_NO")).boardTitle(rs.getString("BOARD_TITLE"))
				.boardWriter(new MemberService().selectById(rs.getString("BOARD_WRITER")))
				.boardContent(rs.getString("BOARD_CONTENT"))
				.boardOriginalFilename(rs.getString("BOARD_ORIGINAL_FILENAME"))
				.boardRenamedFilename(rs.getString("BOARD_RENAMED_FILENAME"))
				.boardReadCount(rs.getInt("BOARD_READ_COUNT")).build();
	}
}
