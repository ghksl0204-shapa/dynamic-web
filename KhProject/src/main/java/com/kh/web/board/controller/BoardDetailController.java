package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.board.model.dto.BoardResponse;
import com.kh.web.board.model.service.BoardService;

@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		BoardResponse board = new BoardService().selectBoard(boardNo);
		
		if(board != null) {
			request.setAttribute("board", board.getBoard());
			request.setAttribute("attachment", board.getAttachment());
			request.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("message", "게시글 조회 실패");
			response.sendRedirect("/kh/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
