<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
	URI > URL
	
	URI(Uniform Resource Identifier) : 자원을 식별하는 문자열 / 자원의 위치, 네이밍과 상관없이 고유하게 카리킬 수 있는 모든 식별자
	
	URL(Uniform Resource Locator) : Location(위치) 어디로 가면 그걸 찾을 수 있는가에 대한 답
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중요!</title>
</head>
<body>

	<h1>JSTL이란...?</h1>

	<p>
		Jakarta Server Page Standard Tag Library의 약어로 <br>
		JSP에서 사용할 수 있는 커스텀 액션 태그 <br>
		공통적으로 화면단에서 사용하게 되는 코드들의 어떤 집합을 보다 쉽게 사용할 수 있게끔 태그로 만들어서 <br>
		표준으로 제공하는 라이브러리(클래스 모음집)
	</p>
	
	<hr>
	
	<h3>* 라이브러리를 추가</h3>
	
	<ol>
		<li>https://tomcat.apache.org로 접속</li>
		<li>Standard-1.2.5.jar파일 4개 다 다운받기</li>
		<li>WEB-INF/lib폴더에 넣어준다(Copy files)</li>
	</ol>
	
	<h4>* JSTL 선언!!</h4>  
	
	<%
		request.setAttribute("abc", 10);
	%>
	
	<c:set var="bbb" value="10" />
	
	<p>
		JSTL을 사용하는 방법은 일단 사용하고자 하는 JSP파일 상단에 <br>
		보통 page지시어 바로 밑에 <br>
		taglib지시어를 사용해서 선언을 해주어야함!!
	</p>
	
	<hr>
	
	<h4>JSTL Core Library</h4>
	
	<p>
		JSP상에서 조건문, 반복문등의 로직과 관련된 태그들을 제공함
	</p>

	<h5>속성 추가하기(Attribute추가)</h5>
	
	<pre>
		활용빈도 낮음
		
		&lt; c:set var="키값" value="리터럴값" scope="스코프(생략가능)" />
		- Scope에 새로운 Atrribute를 추가하는 태그
		- scope 속성 생략시 pageScope에 담김
	</pre>

	<c:set var="num1" value="10" />
	<c:set var="num2" value="20" scope="request" />
	<c:set var="result" value="${ num1 + num2 }" scope="session" />
	
	set태그로 선언한 Attribute는 EL구문을 이용해서 출력 <br>
	num1의 값 : ${ num1 } <br>
	num2의 값 : ${ num2 } <br>
	result의 값 : ${ result } <br>
	
	<c:set var="result" scope="request">9999</c:set>
	
	<hr>
	
	<pre>
		활용빈도 : 중간
		
		* 속성 삭제(&lt;c:remove var="제거하고자하는 속성" scope="스코프영역(생략가능)") />)
		
		- 해당 Attribute를 Scope영역에서 제거하는 태그
		- scope속성을 작성하지 않으면 모든 scope에서 해당 Attribute를 싹 다 제거함
	</pre>
	
	삭제 전 result : ${ result } <br>
	
	<hr>
	
	<c:remove var="result" scope="request"/>
	삭제 후 result : ${ result } <br>
	
	<hr>

	<pre>
		활용빈도 : 낮은데 진짜 살다가 필요할때가 생김
		
		* 출력(&lt;c:out value="출력할 값" default="기본값" escapeXml="t/f"/>)
		- 속성을 출력하려고할 때 사용하는 태그
		
	</pre>
	
	num1 : <c:out value="${ num1 }" /> <br>
	ㅋㅋㅋ : ${ num1 } <br>
	
	requestScope result : ${ requestScope.result } <br>
	out태그 사용 : <c:out value="${ request.result }" default="값이 존재하지 않음" /> <br>
	
	<c:set var="strong" value="<strong>강한정보</strong>" />
	
	${ strong }
	<c:out value="${ strong }" />
	
	<hr>
	
	<h3>조건문</h3>
	
	<pre>
		활용빈도 : 높음
		
		&lt;c:if test="조건식">
			조건식이 true일 경우 출력할 내용
		&lt;/c:if>
		
		- 조건식을 작성할 때는 반드시 EL구문으로 작성
	</pre>
	
	<c:if test="${ num1 lt num2 }">
		<strong>num1dl num2보다 작습니다.</strong><br>
	</c:if>
	
	<h3>choose, when, otherwise</h3>
	
	<pre>
		활용빈도 : 매우높음
		
		&lt;c:choose>
			&lt;c:when test="조건1">
				출력할 내용1
			&lt;/c:when>
			&lt;c:when test="조건2">
				출력할 내용2
			&lt;/c:when>
			&lt;c:otherwise>
				출력할 내용3
			&lt;/c:otherwise>
		&lt;/c:choose>
	</pre>
	
	<c:set var="point" value="400" />
	
	회원 등급 출력 :
	<!-- 아 조건식을 꼭 EL구문으로 써야지! -->>
	<c:choose>
		<c:when test="${ ponit le 100 }">
			일반회원
		</c:when>
		<c:when test="${ point le 300 }">
			우수회원
		</c:when>
		<c:otherwise>
			최우수회원
		</c:otherwise>
	</c:choose>
	<!-- choose구문안에 when/otherwise 말고 다른 어떤 무언가가 들어가서는 안됨! -->>	
	<br><br>
	
	<h3>반복문</h3>
	
	<% for(int i = 0; i < 10; i++) { %>
		<%= i %>
	<% } %>

	<pre>
		활용빈도 : 매우 높음
		
		for loop문
		&lt;c:forEach var="속성명" begin="초기값" end="끝값" step="증가치">
			반복시킬 내용
		&lt;/c:forEach>
		
		step은 생략시 기본값 == 1
		
		향상된 for문
		&lt;c:forEach var="속성명" items="순차적으로 요소에 접근할 배열 / 컬렉션" varStatus="상태값">
			반복시킬내용
		&lt;/c:forEach>
		
		var로 선언된 제어변수의 경우 반드시 EL구문으로 접근해야함!!
	</pre>
	
	<c:forEach var="i" begin="0" end="9">
		${ i }
	</c:forEach>
	
	<br>
	
	<c:forEach var="i" begin="1" end="6">
		<h${ i }>이것도 됨</h${ i }>
	</c:forEach>

	<c:set var="color">
		red, orangered, orange, yellow, yellowgreen, greenyellow, forestgreen
	</c:set>
	
	color : ${ colors }
	
	<br>
	
	<ul>
		<c:forEach var="c" items="${ colors }">
			<li style="color:${ c }">${ c }</li>
		</c:forEach>
	</ul>

	<hr>
	
	<table border="1">
		<thead>
			<tr>
				<th>순번</th>
				<th>이름</th>
				<th>나이</th>
				<th>주소</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${ empty persons }">
					<tr>
						<th colspan="4"> 조회결회가 존재하지 않습니다.</th>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="p" items="${ persons }" varStatus="s">
						<tr>
							<td>${s.count }</td>
							<td>${p.name }</td>
							<td>${p.age }</td>
							<td>${p.address }</td>
						</tr>
					</c:forEach>
				</c:otherwise>			
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="3">총 합</th>
				<th>${ persons.size() }명</th>
			</tr>
		</tfoot>	
	</table>


	<h5>forTokens</h5>
	
	<c:set var="device" value="컴퓨터, 핸드폰, TV/에어컨. 냉장고-세탁기" />
	
	<ul>
		<c:forTokens var="d" items="${ device }" delims=",/.-">
			<li>${ d }</li>
		</c:forTokens>
	</ul>














	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>