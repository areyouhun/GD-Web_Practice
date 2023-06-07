package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.common.AESEncryptor;
import com.web.member.model.dto.Member;
import com.web.member.service.MemberService;

@WebServlet(name="memberView", urlPatterns="/member/memberView.do")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberViewServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Object obj = request.getSession().getAttribute("memberLoggedIn");
		String userId = Member.class.cast(obj).getUserId();
		
		Member member = new MemberService().selectById(userId);
		
		try {
			member.setEmail(AESEncryptor.decrypt(member.getEmail()));
		} catch (Exception e) {
			System.out.println("이메일 암호화 실패");
		}
		
		try {
			member.setPhone(AESEncryptor.decrypt(member.getPhone()));
		} catch (Exception e) {
			System.out.println("전화번호 암호화 실패");
		}
		
		request.setAttribute("memberInfo", member);
		request.getRequestDispatcher("/views/member/memberView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
