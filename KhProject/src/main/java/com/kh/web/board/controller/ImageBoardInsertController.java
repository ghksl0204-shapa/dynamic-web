package com.kh.web.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.service.BoardService;
import com.kh.web.common.MyRenamePolicy;
import com.kh.web.member.model.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/insert.im")
public class ImageBoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImageBoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			String savePath = request.getServletContext().getRealPath("/resources/image_upfiles");
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			HttpSession session = request.getSession();
			MemberDto member = (MemberDto)session.getAttribute("userInfo"); // Null 체크 생략
			if(member == null) {
				session.setAttribute("message", "로그인 후 글쓰기가 가능합니다");
				response.sendRedirect("/kh/fail.do");
				return;
			}
			Long userNo = member.getUserNo();
			BoardDto board = new BoardDto();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setUserNo(userNo);
			
			List<AttachmentDto> files = new ArrayList();
			for(int i = 0; i < 5; i++) {
				String key = "file" + i;
				if(multiRequest.getOriginalFileName(key) != null) {
					AttachmentDto at = new AttachmentDto();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/image_upfiles");
					at.setBoardType("I");
					at.setFileLevel(i == 1 ? 1 : 2);
					files.add(at);
				}
			}
			
			int result = new BoardService().insertImage(board, files);
			
			if(result > 0) {
				session.setAttribute("alretMsg", "게시글 등록 성공");
				response.sendRedirect(request.getContextPath() + "/boards.im");
			} else {
				session.setAttribute("message", "뭔가 실패함");
				response.sendRedirect(request.getContextPath() + "/fail.do");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
