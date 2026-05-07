package com.kh.web.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.web.common.Template;
import com.kh.web.member.model.dao.MemberDao;
import com.kh.web.member.model.dto.MemberDto;

public class MemberService {

	private MemberDao md = new MemberDao();

	public int insertMember(MemberDto member) {
		
		SqlSession sqlSession = Template.getSqlSession();
		int result = md.insertMember(sqlSession, member);
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	public MemberDto login(MemberDto member) {
		// 로그인 처리 -> DAO로 전달값을 전달해서 SELECT해보기 -> 결과값반환
		// 전통적인 session방식 로그인은 조회된 행의 정보를 객체의 필드에 담아서 반환
		// validate(member); 비즈니스 로직 ~~
		SqlSession sqlSession = Template.getSqlSession();
		MemberDto loginMember = md.login(sqlSession, member);
		sqlSession.close();
		return loginMember;
	}
	
	
	
}
