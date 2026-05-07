package com.kh.web.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {

	public static SqlSession getSqlSession() {
		
		SqlSession session = null;
		String config = "/mybatis-config.xml";
		// /는 모든 src폴더의 최상위 의미(classes)
		try {
			InputStream stream = Resources.getResourceAsStream(config);
			session = new SqlSessionFactoryBuilder().build(stream).openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
