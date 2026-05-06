<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여기는 포워드페이지</h1>
	
	<jsp:forward page="footer.jsp" />
	
	<!-- 
		URL에는 http://localhost:8088/action/forward.do가 찍혀있음
		URL은 그대로고 응답데이터만 바뀜
	 -->

</body>
</html>