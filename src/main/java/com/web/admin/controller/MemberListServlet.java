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

@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberListServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 페이징
		int totalData = new AdminService().selectMemberCount();
		int dataPerPage = 10;
		int totalPages = (int) Math.ceil((double) totalData / dataPerPage);
		
		int currentPage;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		int pageBarSize = 5;
		int pageStartNo = ((currentPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEndNo = pageStartNo + pageBarSize - 1;
		
		StringBuilder pageBar = new StringBuilder();
		
		if (pageStartNo == 1) {
			pageBar.append("<span class='prev'>[이전]</span>");
		} else {
			pageBar.append(String.format("<a href='%s?currentPage=%d' class='prev'>[이전]</a>", request.getRequestURI(), (pageStartNo - 1)));
		}
		
		while (pageStartNo <= pageEndNo && pageStartNo <= totalPages) {
			if (pageStartNo == currentPage) {
				pageBar.append(String.format("<span class='fw-bolder'>%d</span>", pageStartNo));
			} else {
				pageBar.append(String.format("<a href='%s?currentPage=%d'>%d</a>", request.getRequestURI(), pageStartNo, pageStartNo));
			}
			pageStartNo++;
		}
		
		if (pageStartNo > totalPages) {
			pageBar.append("<span class='next'>[다음]</span>");
		} else {
			pageBar.append(String.format("<a href='%s?currentPage=%d' class='next'>[다음]</a>", request.getRequestURI(), pageStartNo));
		}
				
		// 회원정보 받아오기
		List<Member> members = new AdminService().selectMemberAll(currentPage, dataPerPage);
		
		// 최종 전달
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
