package com.kh.web.board.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dao.BoardDao;
import com.kh.web.board.model.dto.BoardDto;
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

}
