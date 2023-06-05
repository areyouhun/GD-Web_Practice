package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.member.model.dto.Member;
import com.web.member.service.MemberService;

@WebServlet("/updatePasswordEnd.do")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePasswordEndServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		String userId = request.getParameter("userId");
		String currentPw = request.getParameter("password");
		String newPw = request.getParameter("password_new");
		Member member = new MemberService().selectByAccount(userId, currentPw);
		
		String msg = "비밀번호 수정에 실패했습니다.";
		String loc = "/member/updatePassword.do";
		
		if (member == null) {
			msg = "현재 비밀번호가 일치하지 않습니다.";
		} else {
			int result = new MemberService().updatePassword(userId, newPw);
			
			if (result == 0) {
				msg = "비밀번호 수정에 실패했습니다.";
			} else {
				msg = "비밀번호가 성공적으로 수정되었습니다.";
				loc = "/";
				request.setAttribute("script", 
									String.format("window.opener.replace.loaction('%s/logout.do'); window.close();", request.getContextPath())
									);
			}
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
