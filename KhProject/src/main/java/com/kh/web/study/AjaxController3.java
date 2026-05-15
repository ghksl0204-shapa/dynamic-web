package com.kh.web.study;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;

@WebServlet("/ajax3.do")
public class AjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<BoardDto> images = new BoardService().selectImageList();
		
//		BoardDto b1 = images.get(0);
//		JSONObject j1 = new JSONObject();
//		j1.put("boardNo", b1.getBoardNo());
//		j1.put("boardTitle", b1.getBoardTitle());
//		j1.put("src", b1.getSrc());
		
		//Gson : Google이 만든 JSON 처리용 라이브러리
		
		response.setContentType("application/json; charset=UTF-8");
		// 응답 시 객체 하나만 전달한다면 JSON Object형태로 응답
		// 응답 시 리스트 객체를 전달한다면 JSON Array형태 안에 요소로 JSON Object를 만들어서 응답
		// JSON Object의 키값은 모두 객체의 속성명(필드명)!
		new Gson().toJson(images, response.getWriter());
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
