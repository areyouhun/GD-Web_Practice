package com.web.board.service;

import com.web.board.model.dao.BoardDao;

public class BoardService {
	private final BoardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
}
