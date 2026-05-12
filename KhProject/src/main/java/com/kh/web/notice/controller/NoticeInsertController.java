package com.kh.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;
import com.kh.web.notice.model.dto.NoticeDto;
import com.kh.web.notice.model.service.NoticeService;

@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값을 뽑아야해 유저No, 유저Name, 굳이??
		// 넣어야할 것 노티스넘(자동), 작성자, 타이틀, 컨텐츠, 
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		Long userNo = member.getUserNo();
		String userName = member.getUserName();
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		int result = new NoticeService().insertNotice(new NoticeDto(userNo, noticeTitle, noticeContent, userName));
		
		if(result > 0) {
			response.sendRedirect("/kh/notices.do?page=1");
		} else {
			session.setAttribute("message", "공지사항 작성 실패");
			response.sendRedirect("/kh/fail.do");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
