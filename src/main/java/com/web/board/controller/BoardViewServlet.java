package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.dto.Board;
import com.web.board.model.dto.BoardComment;
import com.web.board.service.BoardService;

@WebServlet("/board/boardView.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardViewServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{		
		// 클라이언트가 현재 읽은 게시글의 번호 가져오기
		int currentBoardNo = Integer.parseInt(request.getParameter("no"));
		
		// 클라이언트가 예전에 읽은 게시글 번호 가져오기
		Cookie[] cookies = request.getCookies();
		String prevBoardNumbers = "";
		boolean isRead = false;
		
		// 예전에 읽은 게시글 번호 중에 현재 읽은 게시글 번호가 있다면 변수 isRead를 true로 변환
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("boardRead")) {
					prevBoardNumbers = cookie.getValue();
					if (prevBoardNumbers.contains("|" + currentBoardNo + "|")) {
						isRead = true;
					}
					break;
				}
			}
		}
		
		// 변수 isRead가 false일 때 (처음 읽은 글일 때) 쿠키에 해당 게시글 번호를 추가
		if (isRead == false) {
			Cookie cookie = new Cookie("boardRead", prevBoardNumbers + "|" + currentBoardNo + "|");
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		}
		
		Board board = new BoardService().selectBoardByNo(currentBoardNo, isRead);
		List<BoardComment> boardComments = new BoardService().selectBoardCommentByBoardNo(currentBoardNo);
		
		String destination = "/views/board/boardView.jsp";
		if (board != null) {
			request.setAttribute("board", board);
			request.setAttribute("boardComments", boardComments);
		} else {
			destination = "/views/common/msg.jsp";
			request.setAttribute("msg", "존재하지 않는 게시물입니다");
			request.setAttribute("loc", "/board/boardList.do");
		}
		request.getRequestDispatcher(destination).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
