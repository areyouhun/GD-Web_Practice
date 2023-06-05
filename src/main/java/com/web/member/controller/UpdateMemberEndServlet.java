package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.service.MemberService;

@WebServlet("/member/updateMemberEnd.do")
public class UpdateMemberEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateMemberEndServlet() {}

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
		
		int result = new MemberService().updateMember(member);
		String msg = "회원정보 수정에 실패했습니다.";
		String loc = "/member/memberView.do";
		
		if (result > 0) {
			msg = "회원정보가 성공적으로 수정되었습니다.";
			request.getSession().setAttribute("memberLoggedIn", member);
		}
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
