package com.kh.web.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.web.ajax.model.dto.ReplyDto;
import com.kh.web.ajax.model.dto.ResponseDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.member.model.dto.MemberDto;

@WebServlet("/insert.re")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		String content = request.getParameter("replyContent");
		
		Long userNo = ((MemberDto)request.getSession().getAttribute("userInfo")).getUserNo();
		
		ReplyDto reply = new ReplyDto();
		reply.setRefBno(boardNo);
		reply.setReplyContent(content);
		reply.setReplyWriter(userNo);
		
		int result = new BoardService().insertReply(reply);
		
		ResponseDto rd = new ResponseDto("201", "잘됐습니다.", null);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(rd, response.getWriter());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
