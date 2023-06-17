package com.web.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.PageBarGenerator;
import com.web.notice.model.dto.Notice;
import com.web.notice.service.NoticeService;

@WebServlet("/notice/noticeList.do")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeListServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		int currentPage = PageBarGenerator.toInteger(request.getParameter("currentPage"), 
													PageBarGenerator.DEFAULT_CURRENT_PAGE);
		int numPerPage = PageBarGenerator.toInteger(request.getParameter("numPerPage"), 
													PageBarGenerator.DEFAULT_NUM_PER_PAGE);
		
		PageBarGenerator pbg = new PageBarGenerator.Builder()
													.uri(request.getRequestURI())
													.currentPage(currentPage)
													.numPerPage(numPerPage)
													.totalData(new NoticeService().selectNoticeCount())
													.pageBarSize(5)
													.build();
		
		StringBuilder pageBar = new StringBuilder();
		pageBar.append(pbg.getBtnToFirst());
		pageBar.append(pbg.getPrevBtn());
		pageBar.append(pbg.getPageBtn());
		pageBar.append(pbg.getNextBtn());
		pageBar.append(pbg.getBtnToLast());
		
		List<Notice> notices = new NoticeService().selectNotice(currentPage, numPerPage);
		
		request.setAttribute("pageBar", pageBar.toString());
		request.setAttribute("notices", notices);
		request.getRequestDispatcher("/views/notice/noticeList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
