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

@WebFilter(servletNames = { 
		"memberView", "updateMemberEnd", "updatePassword" 
})
public class AuthenticationFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = -302126541455035660L;

	public AuthenticationFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException 
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		Object obj = session.getAttribute("memberLoggedIn");
		
		if (obj == null) {
			System.out.println(request.getParameter("userId"));
			request.setAttribute("msg", "잘못된 접근입니다!");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
