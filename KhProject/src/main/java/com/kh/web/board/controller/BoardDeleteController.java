package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.member.model.dto.MemberDto;

@WebServlet("/delete.bo")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			Long boardNo = Long.parseLong(request.getParameter("boardNo"));
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto)session.getAttribute("userInfo");
			if(member == null) {
				session.setAttribute("message", "접근금지 삐~~");
				response.sendRedirect(request.getContextPath() + "/fail.do");
				return;
			}
			Long userNo = member.getUserNo();
			BoardDto board = new BoardDto();
			board.setBoardNo(boardNo);
			board.setUserNo(userNo);
			
			int result = new BoardService().deleteBoard(board);
			
			if(result > 0) {
				session.setAttribute("alerMsg", "추카포카~~");
				response.sendRedirect(request.getContextPath() + "/boards.do?page=1");
			} else {
				response.sendRedirect(request.getContextPath() + "/detail.bo?boardNo=" + boardNo);
			}
			
			
			
			
			
		} catch(NumberFormatException e) {
			request.getSession().setAttribute("message", "너 아주 못된 친구구나??");
			// System.out.println(request.getContextPath());
			response.sendRedirect(request.getContextPath() + "/fail.do");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
