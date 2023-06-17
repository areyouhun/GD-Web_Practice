package com.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.notice.model.dto.Notice;
import com.web.notice.service.NoticeService;

@WebServlet("/notice/noticeView.do")
public class NoticeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeViewServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int no = Integer.parseInt(request.getParameter("no"));
		Notice notice = new NoticeService().selectNoticeByNo(no);
		
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/views/notice/noticeView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
