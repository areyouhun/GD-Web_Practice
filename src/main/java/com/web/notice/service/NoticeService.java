package com.web.notice.service;

import java.sql.Connection;
import java.util.List;

import com.web.common.JDBCTemplate;
import com.web.notice.model.dao.NoticeDao;
import com.web.notice.model.dto.Notice;

public class NoticeService {
	private NoticeDao noticeDao;
	
	public NoticeService() {
		noticeDao =  new NoticeDao();
	}

	public List<Notice> selectNotice(int currentPage, int numPerPage) {
		Connection conn = JDBCTemplate.getConnection();
		List<Notice> notices = noticeDao.selectNotice(conn, currentPage, numPerPage);
		JDBCTemplate.close(conn);
		return notices;
	}

	public int selectNoticeCount() {
		Connection conn = JDBCTemplate.getConnection();
		int count = noticeDao.selectNoticeCount(conn);
		JDBCTemplate.close(conn);
		return count;
	}

	public int insertNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		int result = noticeDao.insertNotice(conn, notice);
		
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public Notice selectNoticeByNo(int no) {
		Connection conn = JDBCTemplate.getConnection();
		Notice notice = noticeDao.selectNoticeByNo(conn, no);
		JDBCTemplate.close(conn);
		return notice;
	}

}
