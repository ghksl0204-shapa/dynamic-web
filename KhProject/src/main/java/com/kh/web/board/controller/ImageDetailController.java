package com.kh.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.board.model.dto.ImageResponse;
import com.kh.web.board.model.service.BoardService;

@WebServlet("/detail.im")
public class ImageDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long num = Long.parseLong(request.getParameter("boardNo"));
		
		ImageResponse ir = new BoardService().selectImageDetail(num);
		
		HttpSession session = request.getSession();
		if(ir != null) {
			session.setAttribute("alertMsg", "조회글 봐랏~~");
			request.setAttribute("board", ir);
			request.getRequestDispatcher("/WEB-INF/views/image_board/detail.jsp").forward(request, response);
		} else {
			session.setAttribute("message", "조회글 실패했다");
			response.sendRedirect(request.getContextPath() + "/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
