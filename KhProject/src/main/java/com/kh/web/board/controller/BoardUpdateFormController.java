package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.board.model.dto.BoardResponse;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.member.model.dto.MemberDto;

@WebServlet("/update-form.bo")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdateFormController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long boardNo = Long.parseLong(request.getParameter("boardNo")); // NumberformatException
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo"); 
		Long userNo = member.getUserNo(); // NullpointException
		/*
		BoardDto board = new BoardDto();
		board.setBoardNo(boardNo);
		board.setUserNo(userNo);
		*/
		BoardResponse board = new BoardService().selectBoard(boardNo);
		
		if(board != null) {
			if(board.getBoard().getUserNo().longValue() != userNo) {
				
			}
			request.setAttribute("board", board);
			request.getRequestDispatcher("/WEB-INF/views/board/update-form.jsp").forward(request, response);
		} else {
			session.setAttribute("message", "존재하지 않는 게시글입니다.");
			response.sendRedirect("/kh/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
