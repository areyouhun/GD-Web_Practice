package com.web.admin.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.common.JDBCTemplate;
import com.web.common.MemberGenerator;
import com.web.common.PropertiesGenerator;
import com.web.member.model.dto.Member;

public class AdminDao {
	private static final String SQL_PATH = "/sql/admin/admin_sql.properties";
	
	private final Properties sql;
	private final String path = AdminDao.class.getResource(SQL_PATH).getPath();
	
	public AdminDao() {
		sql = PropertiesGenerator.by(path);
	}
	
	public List<Member> selectMemberAll(Connection conn, int currentPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberAll"));
			pstmt.setInt(1, (currentPage * numPerPage) - numPerPage + 1);
			pstmt.setInt(2, currentPage * numPerPage);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				members.add(MemberGenerator.by(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return members;
	}

	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberCount"));
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

	public List<Member> selectMemberByKeyword(Connection conn, String type, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<>();
		final String query = sql.getProperty("selectMemberByKeyword").replace("#COL", type);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, keyword.equals("gender") ? keyword : "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				members.add(MemberGenerator.by(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return members;
	}

}
