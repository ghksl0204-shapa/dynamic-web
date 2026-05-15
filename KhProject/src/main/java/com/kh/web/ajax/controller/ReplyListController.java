package com.kh.web.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.web.ajax.model.dto.ReplyDto;
import com.kh.web.ajax.model.dto.ResponseDto;
import com.kh.web.board.model.service.BoardService;

@WebServlet("/list.re")
public class ReplyListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		List<ReplyDto> reply = new BoardService().selectReply(boardNo);
		ResponseDto rd = new ResponseDto("200", "조회성공", reply);
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(rd, response.getWriter());
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
