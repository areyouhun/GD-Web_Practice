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
	
	public List<Member> selectMemberAll(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> members = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberAll"));
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
