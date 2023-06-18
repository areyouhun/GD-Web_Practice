package com.web.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.web.member.model.dto.Member;

@WebFilter(urlPatterns= {"/admin/*", "/notice/insertForm.do"})
public class AdminFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 5019094855642891983L;

	public AdminFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Object adminUncast = session.getAttribute("memberLoggedIn");
		Member admin = null;
		
		if (adminUncast != null && adminUncast instanceof Member) {
			admin = Member.class.cast(adminUncast);
		}
		
		if (admin == null || !admin.getUserId().equals("admin")) {
			req.setAttribute("msg", "잘못된 접근입니다!");
			req.setAttribute("loc", "/");
			req.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
