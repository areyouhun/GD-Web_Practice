package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.service.AdminService;
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
		
		List<Member> members = new AdminService().selectMemberByKeyword(type, keyword);
		
		request.setAttribute("members", members);
		request.getRequestDispatcher("/views/admin/memberManagement.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
