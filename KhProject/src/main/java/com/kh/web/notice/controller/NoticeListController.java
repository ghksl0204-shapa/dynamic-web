package com.kh.web.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.web.common.model.dto.PageInfo;
import com.kh.web.notice.model.dto.NoticeDto;
import com.kh.web.notice.model.service.NoticeService;

@WebServlet("/notices.do")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listCount = new NoticeService().selectNoticeCount();
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageLimit = 5;
		int boardLimit = 3;
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		int offset = (currentPage - 1) * boardLimit;
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, startPage, endPage, maxPage, offset);

		List<NoticeDto> notices = new NoticeService().selectNoticeList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("notices", notices);
		
		request.getRequestDispatcher("/WEB-INF/views/notice/notices.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
