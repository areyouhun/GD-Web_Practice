package com.web.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.admin.model.dao.AdminDao;
import com.web.common.JDBCTemplate;
import com.web.common.PropertiesGenerator;
import com.web.notice.model.dto.Notice;

public class NoticeDao {
private static final String SQL_PATH = "/sql/notice/notice_sql.properties";
	
	private final Properties sql;
	private final String path = AdminDao.class.getResource(SQL_PATH).getPath();
	
	public NoticeDao() {
		sql = PropertiesGenerator.by(path);
	}

	public List<Notice> selectNotice(Connection conn, int currentPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Notice> notices = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectNoticeAll"));
			pstmt.setInt(1, (currentPage * numPerPage) - numPerPage + 1);
			pstmt.setInt(2, currentPage * numPerPage);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				notices.add(getNoticeBy(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return notices;
	}

	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectNoticeCount"));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	
	public int insertNotice(Connection conn, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertNotice"));
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeWriter());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setString(4, notice.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Notice selectNoticeByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectNoticeByNo"));
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				notice = getNoticeBy(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return notice;
	}

	private Notice getNoticeBy(ResultSet rs) throws SQLException {
		return Notice.builder()
					.noticeNo(rs.getInt("NOTICE_NO"))
					.noticeTitle(rs.getString("NOTICE_TITLE"))
					.noticeWriter(rs.getString("NOTICE_WRITER"))
					.noticeContent(rs.getString("NOTICE_CONTENT"))
					.noticeDate(rs.getDate("NOTICE_DATE"))
					.filePath(rs.getString("FILEPATH"))
					.build();
	}
}
