package com.kh.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.notice.model.dto.NoticeDto;
import com.kh.web.notice.model.service.NoticeService;

@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long noticeNo = Long.parseLong(request.getParameter("noticeNo"));
		
		NoticeDto notice = new NoticeService().selectOne(noticeNo);
		
		if(notice != null) {
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/notice/detail.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("message", "공지사항 상세조회에 실패했습니다");
			response.sendRedirect("/kh/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
