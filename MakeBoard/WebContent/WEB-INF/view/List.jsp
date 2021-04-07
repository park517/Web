<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        
        <link rel="stylesheet" href="/css/list.css">
        
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css">
        
        <link
            rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <!-- 부트 스트랩 & 제이쿼리 부분 -->
        <title>Document</title>
    </head>
    <body>
    	<input type="hidden" id="member" value="${login.mname}">
            <div class="table-responsive">
                <h1>게시판</h1>
                <table class="table table-bordered table-hover">
                    <colgroup>
                        <col style="width: 10%;">
                        <col style="width: 40%;">
                        <col style="width: 20%;">
                        <col style="width: 20%;">
                        <col style="width: 10%;">
                    </colgroup>
                    <thead>
                    	<tr>
		                    <th>번호</th>
		                    <th>제목</th>
		                    <th>작성자</th>
		                    <th>작성일</th>
		                    <th>조회수</th>
                    	</tr>
                    </thead>
                    <tbody>
					<c:forEach var="n" items="${list}" varStatus="st" >
					    <tr>
	                        <td>${st.index+1}</td>
	                        <td><a href="/list/detail?bid=${n.bid}">${n.btitle}</a></td>
	                        <td>${n.bwriter}</td>
	                        <td>${n.bdate}</td>
	                        <td>${n.hit}</td>
	                    </tr>
					</c:forEach>
					</tbody>
	           
                </table>
                
                <ul class="pagination">
              
            	</ul>
             </div>

         
        <c:if test="${empty login}">
        	 
             <button id="btn_login" type="button"  onclick="login()">로그인</button>
            
        </c:if>
        <c:if test="${!empty login}">
        	 
        	 <button id ="btn_write"  type="button" onclick="location.href='/list/write'">글쓰기</button>
             <button id="btn_logout" type="button" onclick="logout()">로그아웃</button>
             
        </c:if>


     
	<script src="/js/list.js"></script>
    </body>
</html>