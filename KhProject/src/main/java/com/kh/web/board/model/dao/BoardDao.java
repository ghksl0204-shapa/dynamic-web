package com.kh.web.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.board.model.dto.AttachmentDto;
import com.kh.web.board.model.dto.BoardDto;
import com.kh.web.common.model.dto.PageInfo;

public class BoardDao {

	public int selectBoardCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectBoardCount");
	}

	public List<BoardDto> selectBoardList(SqlSession sqlSession, PageInfo pi) {
		return sqlSession.selectList("boardMapper.selectBoardList", pi);
	}

	public int insertBoard(SqlSession sqlSession, BoardDto board) {
		return sqlSession.insert("boardMapper.insertBoard", board);
	}

	public int insertAttachment(SqlSession sqlSession, AttachmentDto at) {
		return sqlSession.insert("boardMapper.insertAttachment", at);
	}

	public int increaseCount(SqlSession sqlSession, Long boardNo) {
		return sqlSession.update("boardMapper.increaseCount", boardNo);
	}

	public BoardDto selectBoard(SqlSession sqlSession, Long boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoard", boardNo);
	}

	public AttachmentDto selectAttachment(SqlSession sqlSession, Long boardNo) {
		return sqlSession.selectOne("boardMapper.selectAttachment", boardNo);
	}

	public int deleteBoard(SqlSession sqlSession, BoardDto board) {
		return sqlSession.update("boardMapper.deleteBoard", board);
	}

	public int deleteAttachment(SqlSession sqlSession, Long boardNo) {
		return sqlSession.delete("boardMapper.deleteAttachment", boardNo);
	}

}
