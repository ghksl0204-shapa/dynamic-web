package com.kh.web.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
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

@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 값뽑기 => 제목, 내용 => BoardDTO로 가공
		//      => 파일      => 이거저거         => DTO로 가공
		// String boardTitle = request.getParameter("boardTitle");
		// System.out.println(boardTitle);
		// form태그로 요청 했을 때 multipart/form-data형식으로 요청한다면
		// request.getParameter로는 요청 시 전달값을 뽑아낼 수 없음
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("userInfo");
		if(member == null) {
			session.setAttribute("message", "글쓰기는 로그인 이후 가능합니다!");
			response.sendRedirect("/kh/fail.do");
			return;
		}
		// 요즘 가장 일반적인 방법은 => 테이블에 컬럼(ROLE)에 하나 만들기 => ADMIN / USER
		// 1) 요청이 multipart방식으로 잘 왔는가를 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			// System.out.println("요청받았니?");
			// 2) 파일 전송 시 필요한 세팅
			// 2_1) 파일 용량 제한
			int maxSize = 10 * 1024 * 1024;
			// 2_2) 서버의 파일 저장할 경로를 얻어내야함
			// PageContext
			// HttpServletRequest
			// HttpSession
			// ServletContext = getRealPath()
			// request.getServletContext()
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/board_upfiles");
			// System.out.println(savePath);
			// 장점
			// 동적으로 실제 경로 확인 / 서버환경에 관계없이 동작
			// 단점
			// WAR파일 배포 시 파일이 사라짐
			
			// 3. 파일 업로드
			// HttpServletRequest
			// => multipartRequest 객체로 반환
			// => MultipartRequest multiRequest =
			//    new MultipartRequest(request, 저장경로, 용량제한, 인코딩방식, 파일명을 수정해주는 rename메소드를 가지고있는객체);
			// 생성자를 호출하면 자동으로 파일이 업로드
			// 일반적으로 파일의 경우 반드시 파일명을 변경해서 업로드하는 것이 관례
			// 똑같은 파일명을 방지하기 위해서 / 파일명에 한글, 특수문자, 공백문자 포함될 경우 서버에 따라 문제가 발생
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			
			// 꼭 해야하는 작업 => Board테이블에 INSERT하기 위해서 값뽑고 가공
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");
			Long userNo = member.getUserNo();
			
			BoardDto board = new BoardDto();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setUserNo(userNo);
			
			// 첨부파일의 정보 => 선택적
			
			// 첨부파일이 있는지 없는지
			// System.out.println(multiRequest.getOriginalFileName("upfile"));
			AttachmentDto at = null;
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				at = new AttachmentDto();
				// originName, changeName, filePath, boardType, fileLevel
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles");
				at.setBoardType("C");
				at.setFileLevel(2);
			}
			
			int result = new BoardService().insertBoard(board, at);
			
			if(result > 0) {

				response.sendRedirect("/kh/boards.do?page=1");
				
			} else {
				// 영속성작업 => INSERT (BOARD/ATTACHMENT)
				// 실패했을 경우 파일이 존재한다면 파일을 삭제해야함
				if(at != null) {
					new File(savePath + "/" + at.getChangeName()).delete();
				}
				session.setAttribute("message", "게시글 작성 실패");
				response.sendRedirect("/kh/fail.do");
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
