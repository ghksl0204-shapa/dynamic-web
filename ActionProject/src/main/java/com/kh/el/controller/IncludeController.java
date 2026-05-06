package com.kh.el.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/include.do")
public class IncludeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public IncludeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 값 뽑기 -> 가공
		// 2. 요청처리
		// 3. 결과값 반환 / 응답화면 지정
		
		request.getRequestDispatcher("/WEB-INF/views/include.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
