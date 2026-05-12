package com.kh.web.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.common.Template;
import com.kh.web.common.model.dto.PageInfo;
import com.kh.web.notice.model.dao.NoticeDao;
import com.kh.web.notice.model.dto.NoticeDto;

public class NoticeService {
	
	private NoticeDao nd = new NoticeDao();

	public int selectNoticeCount() {
		SqlSession sqlSession = Template.getSqlSession();
		int listCount = nd.selectNoticeCount(sqlSession);
		sqlSession.close();
		return listCount;
	}

	public List<NoticeDto> selectNoticeList(PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		List<NoticeDto> notices = nd.selectNoticeList(sqlSession, pi);
		sqlSession.close();
		return notices;
	}

	public int insertNotice(NoticeDto noticeDto) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = nd.insertNotice(sqlSession, noticeDto);
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	public NoticeDto selectOne(Long noticeNo) {
		SqlSession sqlSession = Template.getSqlSession();
		int result = nd.increaseCount(sqlSession,noticeNo);
		NoticeDto notice = null;
		if(result > 0) {
			sqlSession.commit();
			notice = nd.selectOne(sqlSession, noticeNo);
		}
		sqlSession.close();
		return notice;
	}

	public int deleteNotice(NoticeDto noticeDto) {
		SqlSession sqlSession = Template.getSqlSession();
		NoticeDto notice = nd.selectOne(sqlSession, noticeDto.getNoticeNo());
		int result = 0;
		if(noticeDto.getUserNo().compareTo(notice.getUserNo()) == 0){
			result = nd.deleteNotice(sqlSession, noticeDto.getNoticeNo());
		}
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

}
