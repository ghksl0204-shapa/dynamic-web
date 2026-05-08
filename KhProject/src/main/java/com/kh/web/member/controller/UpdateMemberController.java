package com.kh.web.member.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;
import com.kh.web.member.model.service.MemberService;

@WebServlet("/update.me")
public class UpdateMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateMemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request.setCharacterEncoding("UTF-8"); - 필터로 대체
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		Long userNo = member.getUserNo();
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		
//		Map<String, String> map = new HashMap();
//		map.put("userNo", String.valueOf(userNo));
//		map.put("userName", userName);
//		map.put("email", email);
		Map<String, String> map = Map.of("userName", userName,
				                         "email", email,
				                         "userNo", String.valueOf(userNo));
		
		MemberDto userInfo = new MemberService().updateMember(map);
		
		if(userInfo != null) {
//			member.setUserName(userName);
//			member.setEmail(email);
			session.setAttribute("userInfo", userInfo);
//			request.getRequestDispatcher("/WEB-INF/views/member/my_page.jsp").forward(request, response);
			response.sendRedirect("/kh/mypage.do");
		} else {
			session.setAttribute("message", "정보 수정에 실패했습니다...");
			response.sendRedirect("/kh/fail.do");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
