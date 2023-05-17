<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">사원 테이블</h1>
	<table border = "1" align="center" style="text-align:center;">
		<tr>
			<th>사원번호</th>
			<th>사원명</th>
			<th>부서번호</th>
			<th>직급</th>
			<th>월급</th>
		</tr>
		<c:forEach var="i" items="${list}">
		<tr>
			<td>${i.sabun }</td>
			<td>${i.saname }</td> 
			<td>${i.deptno }</td> 
			<td>${i.sajob }</td>
			<td>${i.sapay }</td>
			
		</tr>
		</c:forEach>
	</table>
</body>
</html>