package com.kh.web.study;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax1.do")
public class AjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1, 2, 3 마지막에
		
		// 응답데이터
		String responseData = "요청처리 성공!";
		
		// 1) 응답데이터 정보 설정
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 출력 스트림 이용해서 응답
		response.getWriter().print(responseData);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
