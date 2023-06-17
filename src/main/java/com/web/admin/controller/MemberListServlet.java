package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.service.AdminService;
import com.web.common.PageBarGenerator;
import com.web.member.model.dto.Member;

@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_CURRENT_PAGE = 1;
	private static final int DEFAULT_NUM_PER_PAGE = 5;
       
    public MemberListServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 페이징
		int currentPage = PageBarGenerator.toInteger(request.getParameter("currentPage"), 
													DEFAULT_CURRENT_PAGE);
		int numPerPage = PageBarGenerator.toInteger(request.getParameter("numPerPage"), 
													DEFAULT_NUM_PER_PAGE);
		
		PageBarGenerator pbg = new PageBarGenerator.Builder()
				.uri(request.getRequestURI())
				.currentPage(currentPage)
				.numPerPage(numPerPage)
				.totalData(new AdminService().selectMemberCount())
				.pageBarSize(5)
				.build();
				
		StringBuilder pageBar = new StringBuilder();
		pageBar.append(pbg.getBtnToFirst());
		pageBar.append(pbg.getPrevBtn());
		pageBar.append(pbg.getPageBtn());
		pageBar.append(pbg.getNextBtn());
		pageBar.append(pbg.getBtnToLast());
		
		List<Member> members = new AdminService().selectMemberAll(currentPage, numPerPage);
		
		// 최종 전달
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar.toString());
		request.setAttribute("members", members);
		request.getRequestDispatcher("/views/admin/memberManagement.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
