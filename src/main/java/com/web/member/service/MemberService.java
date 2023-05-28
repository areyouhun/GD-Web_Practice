package com.web.member.service;

import java.sql.Connection;

import com.web.member.model.dto.Member;

public class MemberService {
	private final MemberDao memberDao;
	
	public Member selectByAccount(String id, String pw) {
		Connection conn = JDBCTemplate.getConnection();
		Member member = memberDao.selectByAccount(conn, id, pw);
		JDBCTemplate.close(conn);
		return member;
	}
}
