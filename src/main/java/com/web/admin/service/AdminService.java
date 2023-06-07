package com.web.admin.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.common.JDBCTemplate;
import com.web.member.model.dto.Member;

public class AdminService {
	private AdminDao adminDao = new AdminDao();
	
	public List<Member> selectMemberAll() {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> members = adminDao.selectMemberAll(conn);
		JDBCTemplate.close(conn);
		return members;
	}
}
