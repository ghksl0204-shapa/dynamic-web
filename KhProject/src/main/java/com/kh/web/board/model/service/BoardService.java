package com.kh.web.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.ajax.model.dto.ReplyDto;
import com.kh.web.board.model.dao.BoardDao;
import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.board.model.dto.BoardResponse;
import com.kh.web.board.model.dto.ImageResponse;
import com.kh.web.common.Template;
import com.kh.web.common.model.dto.PageInfo;

public class BoardService {
	
	private BoardDao bd = new BoardDao();

	public int selectBoardCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int listCount = bd.selectBoardCount(sqlSession);
		sqlSession.close();
		return listCount;
	}

	public List<BoardDto> selectBoardList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		List<BoardDto> boards = bd.selectBoardList(sqlSession, pi);
		sqlSession.close();
		return boards;
	}

	public int insertBoard(BoardDto board, AttachmentDto at) {
		SqlSession sqlSession = Template.getSqlSession();
		
		String newTitle = board.getBoardTitle().replaceAll("<", "&lt;");
		board.setBoardTitle(newTitle);
		// INSERT 두번
		int result = bd.insertBoard(sqlSession, board);
		
		int atResult = 1;
		if(at != null) {
			at.setRefBno(board.getBoardNo());
			atResult = bd.insertAttachment(sqlSession, at);
		}
		
		// 트랜잭션 처리 => 두 개의 DML구문을 하나의 트랜잭션으로 묶어서 처리
		if(result * atResult > 0) {
			sqlSession.commit();
		} else  {
			sqlSession.rollback();
		}
		
		sqlSession.close();
		
		return (result * atResult);
	}

	public BoardResponse selectBoard(Long boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		// 총 DB에 세 번가야함
		// UPDATE => 조회수를 증가시키고 => COMMIT
		// SELECT => BOARD
		// SELECT => ATTACHMENT
		int result = bd.increaseCount(sqlSession, boardNo);
		BoardResponse br = null;
		if(result > 0) {
			sqlSession.commit();
			BoardDto board = bd.selectBoard(sqlSession, boardNo);
			AttachmentDto attachment = bd.selectAttachment(sqlSession, boardNo);
			br = new BoardResponse();
			br.setBoard(board);
			br.setAttachment(attachment);
		}
		sqlSession.close();
		return br;
	}

	public int deleteBoard(BoardDto board) {
		SqlSession sqlSession = Template.getSqlSession();
		
		// 삭제요청을 보낸 사용자가 로그인도 안하고 요청을 보냈네? -> Servlet에서 컷
		
		// 삭제요청을 보낸 사용자가 BOARD의 작성자랑 다르네?
		// => 요청보낸 사용자의 유저NO 가 게시글의 작성자 유저 NO 랑 동일한가?
		
		BoardDto boardResult = bd.selectBoard(sqlSession, board.getBoardNo());
		if(boardResult.getUserNo().longValue() != board.getUserNo().longValue()) {
			return 0;
		}
		
		// 1. STATUS Y로 UPDATE해야겠구나
		int result = bd.deleteBoard(sqlSession, board);
		
		AttachmentDto attachment = bd.selectAttachment(sqlSession, board.getBoardNo());
		
		if(attachment != null) {
			result *= bd.deleteAttachment(sqlSession, board.getBoardNo());
		}
		
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		return result;
	}

	public int updateBoard(BoardDto board, AttachmentDto at) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		// 1. WEB_BOARD => UPDATE
		//----------------------------
		// 2. WEB_ATTACHMENT => UPDATE
		// 3. WEB_ATTACHMENT => INSERT
		
		int result = bd.updateBoard(sqlSession, board);
		
		// 새 첨부파일이 존재할경우
		if(at != null) {
			if(at.getFileNo() != null) {
				// 기존 첨부파일 존재
				result *= bd.updateAttachment(sqlSession, at);
			} else {
				// 기존 첨부파일 존재 X
				result *= bd.insertAttachment(sqlSession, at);
			}
		} // 딱히 할 거 없음
		if(result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	}

	public List<BoardDto> selectImageList() {
		SqlSession sqlSession = Template.getSqlSession();
		List<BoardDto> boards = bd.selectImageList(sqlSession);
		sqlSession.close();
		return boards;
	}

	public int insertImage(BoardDto board, List<AttachmentDto> files) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = 0;
		try {
			result = bd.insertImage(sqlSession, board);
			if(result > 0) {
				for(AttachmentDto file : files) {
					file.setRefBno(board.getBoardNo());
					result = bd.insertAttachment(sqlSession, file);
					if(result == 0) {
						new RuntimeException();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			result = 0;
		} finally {
			if(result > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
			sqlSession.close();
		}
		return result;
	}

	public ImageResponse selectImageDetail(Long boardNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		ImageResponse ir = bd.selectImageDetail(sqlSession, boardNo);
		
		sqlSession.close();
		
		return ir;
		
	}

	public int insertReply(ReplyDto reply) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = bd.insertReply(sqlSession, reply);
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	public List<ReplyDto> selectReply(Long boardNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		List<ReplyDto> reply = bd.selectReply(sqlSession, boardNo);
		sqlSession.close();
		return reply;
	}

}
