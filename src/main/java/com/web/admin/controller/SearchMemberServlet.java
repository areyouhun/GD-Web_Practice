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

@WebServlet("/admin/searchMember")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchMemberServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String type = request.getParameter("searchType");	
		String keyword = request.getParameter("searchKeyword");	
		int currentPage = PageBarGenerator.toInteger(request.getParameter("currentPage"), 
													PageBarGenerator.DEFAULT_CURRENT_PAGE);
		int numPerPage = PageBarGenerator.toInteger(request.getParameter("numPerPage"), 
													PageBarGenerator.DEFAULT_NUM_PER_PAGE);
		final String uri = request.getRequestURI()
							+ "?searchType=" + type
							+ "&searchKeyword=" + keyword;
		
		PageBarGenerator pbg = new PageBarGenerator.Builder()
				.uri(uri)
				.currentPage(currentPage)
				.numPerPage(numPerPage)
				.totalData(new AdminService().selectMemberByKeywordCount(type, keyword))
				.pageBarSize(5)
				.build();
				
		StringBuilder pageBar = new StringBuilder();
		pageBar.append(pbg.getBtnToFirst());
		pageBar.append(pbg.getPrevBtn());
		pageBar.append(pbg.getPageBtn());
		pageBar.append(pbg.getNextBtn());
		pageBar.append(pbg.getBtnToLast());
		
		List<Member> members = new AdminService().selectMemberByKeyword(type, keyword, 
																		currentPage, numPerPage);
		
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
