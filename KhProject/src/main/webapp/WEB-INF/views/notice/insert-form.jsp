<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>딸깍</title>
<style>
	form {
		width : 90%;
		margin : auto;
	}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp" />
	
	<div class="outer">

        <h2 align="center">게시글 작성하기</h2>
        <br><br> 
		
        <form action="http://localhost:8088/kh/insert.no" method="post" id="insert-form">


            <div class="form-group">
                <label for="usr">제목</label>
                <input type="text" class="form-control" id="usr" name="noticeTitle">
            </div>

            <div class="form-group">
                <label for="comment">내용</label>
                <textarea class="form-control" name="noticeContent" rows="15" id="comment" style="resize:none;"></textarea>
            </div>
            
            <div align="center">
                <button type="submit" class="btn btn-sm btn-info">등록하기</button>
                <button type="button" class="btn btn-sm btn-secondary"
                onclick="">뒤로가기</button>
            </div>

        </form>
        
    </div>
    
    <jsp:include page="../include/footer.jsp" />

</body>
</html>