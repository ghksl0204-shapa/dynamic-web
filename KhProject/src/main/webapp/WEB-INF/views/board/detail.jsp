<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<br/><br/>

	<jsp:include page="../include/header.jsp" />
	
	<div class="outer">
		<div class="container">
		
		<div class="row">
		  <div class="offset-lg-2 col-lg-8">
		    <div class="card">
		      <div class="card-header text-white" style="background-color: #52b1ff;">${ board.boardNo }번 게시물 내용</div>
		      <div class="card-body"> 
		        
		          <div class="form-group">
		            <label>작성자</label>
		            <input type="text" class="form-control" name='writer' value="${ board.userName }" readonly>
		          </div>
		          
		          <div class="form-group">
		            <label>제목</label>
		            <input type="text" class="form-control" name='title' value="${ board.boardTitle }" readonly>
		          </div>
		
		          <div class="form-group">
		            <label>내용</label>
		            <textarea class="form-control" rows="5" name='content' readonly style="resize:none;">${ board.boardContent }</textarea>
		          </div>
		
		          <div class="form-group">
		            <label>첨부파일</label>
				  <c:choose>
				  	<c:when test="${ attachment ne null }">
				  		<img src="${ attachment.filePath }/${ attachment.changeName }"/>
				  	
				  		<a download="${ attachment.originName }" href="${ attachment.filePath }/${ attachment.changeName }">${ attachment.originName }</a>
				  	</c:when>
				  	<c:otherwise>
			            	&nbsp;&nbsp;<span>첨부파일이 존재하지 않습니다.</span>
				  	</c:otherwise>
				  </c:choose>
		          </div>
		         
		          <a class="btn" href="http://localhost:8088/kh/boards.do?page=1"
		             style="background-color: #52b1ff; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
		          >목록</a>&nbsp;&nbsp;
		          
		         
		          <c:if test="${ board.userNo eq userInfo.userNo }">
			          <a 
			            class="btn" 
			            href="http://localhost:8088/kh/update-form.bo?boardNo=${ board.boardNo }"
			      		style="background-color: orange; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
			      		>수정</a>&nbsp;&nbsp;
			          
			          <a 
			            class="btn" 
			            href="http://localhost:8088/kh/delete.bo?boardNo=${ board.boardNo }" 
			      		style="background-color: red; height: 40px; color: white; border: 0px solid #388E3C; opacity: 0.8"
			      		>삭제</a>&nbsp;&nbsp;
		          </c:if>		          
		      </div>
		    </div>
		  </div>
		</div>
		</div>
		<div id="reply-area">
			
			<table class="form-group" align="center">
				<thead>
					<tr>
						<th>댓글작성</th>
						<c:choose>
							<c:when test="${ userInfo ne null }">
								<td>
									<textarea id="replyContent" cols="50" rows="3" style="resize:none;" class="form-control"></textarea>
								</td>
								<td><button onclick="insertReply();" class="btn" style="width : 100%; height : 100%; background-color: #52b1ff; color : white;">댓글등록</button></td>
							</c:when>							
							
							<c:otherwise>
								<td>
									<textarea readonly cols="50" rows="3" style="resize:none;" class="form-control">로그인 후 이용가능한 서비스입니다.</textarea>
								</td>
								<td><button class="btn" style="width : 100%; height : 100%; background-color: #52b1ff; color : white;">댓글등록</button></td>
							</c:otherwise>							

						</c:choose>
					</tr>
				</thead>
				<tbody id="tbody">
				
				</tbody>
			</table>
			<br><br><br><br>
	    </div>
	    
	    <script>
	    
	    	function insertReply(){
	    		$.ajax({
	    			url : 'http://localhost:8088/kh/insert.re',
	    			type : 'post',
	    			data : {
	    				boardNo : ${ board.boardNo },
	    				replyContent : document.querySelector('#replyContent').value
	    			},
	    			success : result => {
	    				console.log(result);
	    				if(result.code === '201'){
	    					alertify.alert('댓글작성성공~');
	    					document.querySelector('#replyContent').value = '';
	    					selectReply();
	    				}
	    			}
	    		});
	    	}
	    	
	    	window.onload = () => {
	    		selectReply();
	    	};
	    	
	    	function selectReply(){
	    		
	    		$.ajax({
	    			url : 'http://localhost:8088/kh/list.re',
	    			type : 'get',
	    			data : {
	    				boardNo : ${board.boardNo}
	    			},
	    			success : result => {
	    				// console.log(result);
	    				const el = result.data.map(e => `
	    											<tr>
	    												<td>\${e.userName}</td>
	    												<td>\${e.replyContent}</td>
	    												<td>\${e.createDate}</td>
	    											</tr>
	    				                      `).join('');
	    				document.querySelector('#tbody').innerHTML = el;
	    			}
	    		})
	    		
	    	}
	    
	    </script>

	</div>
	
	<jsp:include page="../include/footer.jsp" />
	

	
	
</body>
</html>