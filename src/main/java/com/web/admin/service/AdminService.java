package com.web.admin.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.common.JDBCTemplate;
import com.web.member.model.dto.Member;

public class AdminService {
	private AdminDao adminDao = new AdminDao();
	
	public List<Member> selectMemberAll(int currentPage, int numPerPage) {
		Connection conn = JDBCTemplate.getConnection();
		List<Member> members = adminDao.selectMemberAll(conn, currentPage, numPerPage);
		JDBCTemplate.close(conn);
		return members;
	}

	public int selectMemberCount() {
		Connection conn = JDBCTemplate.getConnection();
		int count = adminDao.selectMemberCount(conn);
		JDBCTemplate.close(conn);
		return count;
	}
}
