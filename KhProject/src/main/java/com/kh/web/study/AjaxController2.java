package com.kh.web.study;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.web.board.model.dto.BoardResponse;
import com.kh.web.board.model.service.BoardService;

@WebServlet("/ajax2.do")
public class AjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 값뽑기
		Long boardNo = Long.parseLong(request.getParameter("boardNo"));
		
		BoardResponse board = new BoardService().selectBoard(boardNo);
		
		// { "boardTitle" : "조회한제목", "boardContent" : "조회한내용" }
		
//		String rd = "{\"" + board.getBoard().getBoardTitle() + "\",\"" + board.getBoard().getBoardContent() + "\"}"; 
//		System.out.println(rd);
		
		/*
		 * JSONArray  : 
		 * JSONObject : 
		 */
		
		JSONArray array = new JSONArray();
		array.add(board.getBoard().getBoardTitle());
		array.add(board.getBoard().getBoardContent());
		
		response.setContentType("application/json; charset=UTF-8");
		// response.getWriter().print(array);
		
		JSONObject obj = new JSONObject();
		obj.put("boardTitle", board.getBoard().getBoardTitle());
		obj.put("boardContent", board.getBoard().getBoardContent());
		
		response.getWriter().print(obj);
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
