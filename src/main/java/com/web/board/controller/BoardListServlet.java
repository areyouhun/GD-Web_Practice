package com.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.board.model.dto.Board;
import com.web.board.service.BoardService;
import com.web.common.PageBarGenerator;

@WebServlet("/board/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println(request.getRequestURI());
		int currentPage = PageBarGenerator.toInteger(request.getParameter("currentPage"), 
													PageBarGenerator.DEFAULT_CURRENT_PAGE);
		int numPerPage = PageBarGenerator.toInteger(request.getParameter("numPerPage"), 
													PageBarGenerator.DEFAULT_NUM_PER_PAGE);
		
		PageBarGenerator pbg = new PageBarGenerator.Builder()
												.uri(request.getRequestURI())
												.currentPage(currentPage)
												.numPerPage(numPerPage)
												.totalData(new BoardService().selectBoardCount())
												.pageBarSize(5)
												.build();
		
		StringBuilder pageBar = new StringBuilder();
		pageBar.append(pbg.getBtnToFirst());
		pageBar.append(pbg.getPrevBtn());
		pageBar.append(pbg.getPageBtn());
		pageBar.append(pbg.getNextBtn());
		pageBar.append(pbg.getBtnToLast());
		
		List<Board> boards = new BoardService().selectBoard(currentPage, numPerPage);
		
		request.setAttribute("boards", boards);
		request.setAttribute("pageBar", pageBar.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
