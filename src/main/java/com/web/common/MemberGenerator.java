package com.web.common;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.web.member.model.dto.Member;

public class MemberGenerator {

	public static Member by(ResultSet rs) throws SQLException {
		Member member = Member.builder()
						.userId(rs.getString("USERID"))
						.password(rs.getString("PASSWORD"))
						.userName(rs.getString("USERNAME"))
						.gender(rs.getString("GENDER"))
						.age(rs.getInt("AGE"))
						.email(rs.getString("EMAIL"))
						.phone(rs.getString("PHONE"))
						.address(rs.getString("ADDRESS"))
						.hobby(rs.getString("HOBBY") == null ? null : rs.getString("HOBBY").split(","))
						.enrollDate(rs.getDate("ENROLLDATE"))
						.build();
		
		try {
			member.setEmail(AESEncryptor.decrypt(member.getEmail()));
		} catch (Exception e) {
			// pass
		}
		
		try {
			member.setPhone(AESEncryptor.decrypt(member.getPhone()));
		} catch (Exception e) {
			// pass
		}
		return member;
	}
}
