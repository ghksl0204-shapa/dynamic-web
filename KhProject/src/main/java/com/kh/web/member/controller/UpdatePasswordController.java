package com.kh.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.web.member.model.dto.MemberDto;
import com.kh.web.member.model.dto.UpdatePwdDto;
import com.kh.web.member.model.service.MemberService;

@WebServlet("/update-pwd.me")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePasswordController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		if(member == null) {
			session.setAttribute("message", "로그인을 먼저 진행해주세요!");
			response.sendRedirect("/kh/fail.do");
			return;
		}
		Long userNo = member.getUserNo();
		
		UpdatePwdDto upd = new UpdatePwdDto(userPwd, updatePwd, userNo);
		int result = new MemberService().updatePassword(upd);
		
		if(result > 0) {
			// 성공했을 때는 마이페이지로 돌아간다.
			response.sendRedirect("/kh/mypage.do");
		} else {
			session.setAttribute("message", "비밀번호 변경에 실패했습니다.");
			response.sendRedirect("/kh/fail.do");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
