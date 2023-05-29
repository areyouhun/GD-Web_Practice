package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.service.MemberService;

@WebServlet("/login.do")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogInServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		Member member = new MemberService().selectByAccount(userId, userPw);
		
		if (member != null) {
			request.getSession().setAttribute("memberLoggedIn", member);
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
