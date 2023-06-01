package com.web.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.web.common.JDBCTemplate;
import com.web.common.PropertiesGenerator;
import com.web.member.model.dto.Member;

public class MemberDao {
	private static final String SQL_PATH = "/sql/member/member_sql.properties";
	
	private final Properties sql;
	private final String path = MemberDao.class.getResource(SQL_PATH).getPath();
	
	public MemberDao() {
		sql = PropertiesGenerator.by(path);
	}

	public Member selectByAccount(Connection conn, String id, String pw) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectByAccount"));
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				member = generateMemberBy(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return member;
	}
	
	public int enrollMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql.getProperty("enrollMember"));
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, String.join(",", member.getHobby()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	private Member generateMemberBy(ResultSet rs) throws SQLException {
		return Member.builder()
				.userId(rs.getString("USERID"))
				.password(rs.getString("PASSWORD"))
				.userName(rs.getString("USERNAME"))
				.gender(rs.getString("GENDER"))
				.age(rs.getInt("AGE"))
				.email(rs.getString("EMAIL"))
				.phone(rs.getString("PHONE"))
				.address(rs.getString("ADDRESS"))
				.hobby(rs.getString("HOBBY").split(","))
				.enrollDate(rs.getDate("ENROLLDATE"))
				.build();
	}
}
