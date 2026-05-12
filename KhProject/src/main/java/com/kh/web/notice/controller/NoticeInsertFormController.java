package com.kh.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;

@WebServlet("/insertform.no")
public class NoticeInsertFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeInsertFormController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		// 1. 로그인해야함
		if(member == null) {
			session.setAttribute("message", "로그인해야지???");
			response.sendRedirect(request.getContextPath() + "/fail.do");
			// 2. 관리자여야함
		} else if("C".equals(member.getRole())) {
			session.setAttribute("message", "관리자만 글쓴다????");
			response.sendRedirect(request.getContextPath() + "/fail.do");
		} else {
			request.getRequestDispatcher("/WEB-INF/views/notice/insert-form.jsp").forward(request, response);
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
