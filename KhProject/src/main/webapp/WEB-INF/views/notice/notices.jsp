<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<style>

header.masthead {
   display: none;
}   
.row{
	height : 800px;
}
tr:hover{
	cursor : pointer;
}

</style>

<br/><br/> 
 
   <jsp:include page="../include/header.jsp"/>

   <!-- Begin Page Content -->
   <div class="container">
      <div class="row">
         <div class="col-lg-1">
         </div>
         <div class="col-lg-10">
            <div class="panel-body">
            <h2 class="page-header"><span style="color: #52b1ff;">KH</span> 공지 게시판
               <a href="http://localhost:8088/kh/insertform.no" class="btn float-right" style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">글쓰기</a>
            </h2>
               <table class="table table-bordered table-hover">
                  <thead>
	                  <tr style="background-color: #52b1ff; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
	                     <th width="100">번호</th>
	                     <th width="150">작성자</th>
	                     <th width="450">제목</th>
	                     <th width="200">작성일</th>
	                     <th width="100">조회수</th>
	                  </tr>
                  </thead>
                  <tbody>
	           
                    <c:choose>
                    	<c:when test="${ empty notices }">
                    		<tr>
                    			<th colspan="5"> 조회 결과가 존재하지 않습니다.</th>
                    		</tr>
                    	</c:when>
                    	<c:otherwise>
                    		<c:forEach var="notice" items="${ notices }">
			                    <tr style="color: #52b1ff;"
			                        class="notice"
			                        id="1">
			                        <td>
			                        ${ notice.noticeNo }
			                        </td>
			                        <td>
			                        ${ notice.userName }
			                        </td>
			                        <td style="color: #52d6ffcc;">
			                        <a href="http://localhost:8088/kh/detail.no?noticeNo=${ notice.noticeNo }">
			                        ${ notice.noticeTitle } &nbsp;
			                        </a>
			                        </td>
			                        <td>
			                        ${ notice.createDate }
			                        </td>
			                        <td>
			                        ${ notice.count }
			                        </td>
			                    </tr>    
                    		</c:forEach>
                    	</c:otherwise>
                    </c:choose>	           	  
 
                    
	        
                  </tbody>
                  
               </table> 
               <div id="search-area" class="form-group">
				<form action="" method="get">
					<select name="condition" class="form-control">
						<option value="writer">작성자</option>
						<option value="content">내용</option>
						<option value="title">제목</option>
					</select>
					<button type="submit" class="btn btn-block" style="background:#52b1ff; color:white">검색</button>
				</form>
		     </div>              
             </div>
	         <div class="paging-area" align="center" >
   	        	   	<c:if test="${ pi.currentPage gt 1 }">
   		                <button 
		                class="btn btn-outline-primary" style="color:#52b1ff;"
		                onclick="location.href='http://localhost:8088/kh/notices.do?page=${pi.currentPage - 1}'">이전</button>
	        	   	</c:if>
	        	   <c:forEach var="i" begin="${ pi.startPage }" end="${ pi.endPage }">
		                <button 
		                class="btn btn-outline-primary" style="color:#52b1ff;"
		                onclick="location.href='http://localhost:8088/kh/notices.do?page=${i}'">${ i }</button>
	        	   
	        	   </c:forEach>
	        	   	<c:if test="${ pi.currentPage ne pi.maxPage }">
   		                <button 
		                class="btn btn-outline-primary" style="color:#52b1ff;"
		                onclick="location.href='http://localhost:8088/kh/notices.do?page=${pi.currentPage + 1}'">다음</button>
	        	   	</c:if>
   		                <button 
		                class="btn btn-outline-primary" style="color:#52b1ff;"
		                onclick="location.href='http://localhost:8088/kh/notices.do?page=${pi.endPage + 1}'">다다음</button>
	        	   
	         </div>
         </div>
      </div>
   </div>
   
     
     
   <jsp:include page="../include/footer.jsp"/>
    