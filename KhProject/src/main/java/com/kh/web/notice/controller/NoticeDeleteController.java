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

@WebServlet("/delete.no")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 사용자와 게시글 사용자가 같은 사람인지 번호로 확인
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		if(member == null) {
			session.setAttribute("message", "로그인 후에 사용하세요?");
			response.sendRedirect("/kh/fail.do");
		} 
		NoticeDto notice = new NoticeDto();
		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		notice.setNoticeNo(noticeNo);
		notice.setUserNo(member.getUserNo());
		
		int result = new NoticeService().deleteNotice(notice);
		if(result > 0 ) {
			response.sendRedirect("/kh/notices.do?page=1");
		} else {
			session.setAttribute("message", "공지사항 삭제 실패");
			response.sendRedirect("/kh/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
