package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.service.MemberService;

@WebServlet({ "/EnrollMemberEndServlet", "/member/enrollMemberEnd.do" })
public class EnrollMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnrollMemberEndServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Member member = Member.builder()
							.userId(request.getParameter("userId"))
							.password(request.getParameter("password"))
							.userName(request.getParameter("userName"))
							.gender(request.getParameter("gender"))
							.age(Integer.parseInt(request.getParameter("age")))
							.email(request.getParameter("email"))
							.phone(request.getParameter("phone"))
							.address(request.getParameter("address"))
							.hobby(request.getParameterValues("hobby"))
							.build();
		
		int result = new MemberService().enrollMember(member);
		String msg = "회원가입을 축하드립니다!";
		String loc = "/";
		
		if (result == 0) {
			msg = "회원가입에 실패했습니다.";
			loc = "/member/enrollMember.do";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
