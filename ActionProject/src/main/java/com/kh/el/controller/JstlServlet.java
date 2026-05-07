package com.kh.el.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.el.model.dto.Person;

@WebServlet("/jstl.do")
public class JstlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JstlServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DB 갔다온척 -> SELECT
		// List가 응답으로 옴
		
		List<Person> persons = new ArrayList();
		persons.add(new Person("홍길동", 15, "한양"));
		persons.add(new Person("고길동", 40, "마포"));
		persons.add(new Person("허균", 22, "스울"));
		
		request.setAttribute("persons", persons);
		
		
		request.getRequestDispatcher("/WEB-INF/views/custom/JSTL.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
