<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 

    <jsp:include page="../include/header.jsp" />

    <div class="outer">

        <h2 align="center">사진게시글 작성하기</h2>
        <br><br>

        <form action="" 
            enctype="multipart/form-data"
            method="post" id="insert-form" style="width:800px;margin:auto;">
        
            <div class="form-group">
                <label for="usr">제목</label>
                <input type="text" class="form-control" id="usr" name="">
            </div>

            <div class="form-group">
                <label for="comment">내용</label>
                <textarea class="form-control" name="" rows="15" id="comment" style="resize:none;"></textarea>
            </div>

            <div class="form-group" align="center" >
                <label>대표이미지</label><br>
                <img width="300" id="title-img" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="대표이미지">
            </div>
            <div class="form-group" align="center" style="display:inline-block">
                <label>상세이미지-1</label><br>
                <img width="250" id="sub-img1" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="상세이미지1">
            </div>
            <div class="form-group" align="center" style="display:inline-block; margin-left:19px;">
                <label>상세이미지-2</label><br>
                <img width="250" id="sub-img2" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="상세이미지2">
            </div>
            <div class="form-group" align="center" style="display:inline-block; margin-left:19px;">
                <label>상세이미지-3</label><br>
                <img width="250" id="sub-img3" height="180" src="https://kh-academy.co.kr/resources/images/main/logo.svg" alt="상세이미지3">
            </div>

            <div id="file-area">
                <input type="file" name="file1" id="file1" required onchange="loadImg(this, 1)">
                <input type="file" name="file2" id="file2" onchange="loadImg(this, 2)">
                <input type="file" name="file3" id="file3" onchange="loadImg(this, 3)">
                <input type="file" name="file4" id="file4" onchange="loadImg(this, 4)">
            </div>
            
            <script>
            
            	$(function(){
            		$('#file-area').hide();
            		
            		$('#title-img').click(() => {
            			$('#file1').click();
            		});
            	});
            	
            
            </script>
            
            
            <script>
            	function loadImg(e, num){
            		//console.log(e);
            		//console.log(num);
            		// e : 현재 change이벤트가 발생한 <input type="file"> 요소객체
            		// num : 몇 번째 요소인지 확인 후 미리보기 할려고 받아옴
            		
            		// console.log(e.files);
            		// files : 첨부한 파일의 정보를 가지고 있음
            		// files.length : 첨부하면 1, 취소 0
            		// 파일이 첨부되었을 경우 e.files[0]에 파일의 정보를 파악할 수 있음
            		
            		if(e.files.length === 1){
            			// 선택된 파일을 읽어서 이미지를 띄울 수 있는 URL을 생성
            			// 필요한객체 == fileReader
            			const reader = new FileReader();
            			
            			// reader 메소드 중 파일을 읽어서 URL로 만드는 메소드
            			reader.readAsDataURL(e.files[0]);
            			
            			// 파일을 읽는 순간 고유한 URL이 생김
            			// URL을 미리보기 영역의 img태그의 src속성값으로 대입할 것
            			
            			// 파일 읽기가 완료되면 실행할 이벤트 핸들러를 정의
            			reader.onload = function(e){
            				console.log(e);
            				const url = e.target.result;
            				//document.querySelector('#title-img').src = url;
            				
            				switch(num){
            				case 1 : $('#title-img').attr('src', url); break;
            				case 2 : $('#sub-img1').attr('src', url); break;
            				case 3 : $('#sub-img2').attr('src', url); break;
            				case 4 : $('#sub-img3').attr('src', url); break;
            				}
            				
            			}
            		
            		} else {
            			
            			const url = 'https://kh-academy.co.kr/resources/images/main/logo.svg';
            			
        				switch(num){
        				case 1 : $('#title-img').attr('src', url); break;
        				case 2 : $('#sub-img1').attr('src', url); break;
        				case 3 : $('#sub-img2').attr('src', url); break;
        				case 4 : $('#sub-img3').attr('src', url); break;
        				}
            		}
            		
            		
            	}
            
            
            </script>
            

            <div align="center" style="margin-top:20px">
                <button type="submit" class="btn btn-sm btn-info">등록하기</button>
                <button type="button" class="btn btn-sm btn-secondary"
                onclick="">뒤로가기</button>
            </div>

        </form>
        
    </div>
    
	<jsp:include page="../include/footer.jsp" />

</body>
</html>