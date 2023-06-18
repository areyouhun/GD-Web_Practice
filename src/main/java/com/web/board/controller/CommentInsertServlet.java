package com.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.dto.BoardComment;
import com.web.board.service.BoardService;

@WebServlet("/board/insertComment.do")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentInsertServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// boardCommentRef를 받는 이유는 댓글, 답글, 답글의 답글을 모두 이 서블릿에서 처리할 예정이기 때문
		BoardComment bc = BoardComment.builder()
									.boardRef(Integer.parseInt(request.getParameter("boardRef")))
									.level(Integer.parseInt(request.getParameter("level")))
									.boardCommentWriter(request.getParameter("boardCommentWriter"))
									.boardCommentContent(request.getParameter("boardCommentContent"))
									.boardCommentRef(Integer.parseInt(request.getParameter("boardCommentRef")))
									.build();
		
		int result = new BoardService().insertBoardComment(bc);
		if (result > 0) {
			// dispatcher로 보내면 새로고침할 때마다 댓글이 계속 등록됨
			response.sendRedirect(request.getContextPath() + "/board/boardView.do?no=" + bc.getBoardRef());
		} else {
			request.setAttribute("msg", "댓글등록 실패!");
			request.setAttribute("loc", "/board/boardView.do?no=" + bc.getBoardRef());
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
