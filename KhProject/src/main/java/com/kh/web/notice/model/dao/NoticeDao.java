package com.kh.web.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.common.model.dto.PageInfo;
import com.kh.web.notice.model.dto.NoticeDto;

public class NoticeDao {

	public int selectNoticeCount(SqlSession sqlSession) {
		return sqlSession.selectOne("noticeMapper.selectNoticeCount");
	}

	public List<NoticeDto> selectNoticeList(SqlSession sqlSession, PageInfo pi) {
		return sqlSession.selectList("noticeMapper.selectNoticeList", pi);
	}

	public int insertNotice(SqlSession sqlSession, NoticeDto noticeDto) {
		return sqlSession.insert("noticeMapper.insertNotice", noticeDto);
	}

	public int increaseCount(SqlSession sqlSession, Long noticeNo) {
		return sqlSession.update("noticeMapper.increaseCount", noticeNo);
	}
	
	public NoticeDto selectOne(SqlSession sqlSession, Long noticeNo) {
		return sqlSession.selectOne("noticeMapper.selectOne", noticeNo);
	}

	public int deleteNotice(SqlSession sqlSession, Long noticeNo) {
		return sqlSession.update("noticeMapper.deleteNotice", noticeNo);
	}




}
