<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.util.*"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>



<body>
<center>
	<h2>업로드된 파일의 정보는 다음과 같습니다.</h2>
	<table border="2">
		<tr>
			<td bgcolor="blue">작성자</td>
			<td>${requestScope.user}</td>
		</tr>
		<tr>
			<td  bgcolor="blue">내용</td>
			<td>${content}</td>
		</tr>
		<tr>
			<td bgcolor="blue">업로드 된 파일</td>
			<td>
			<c:choose>
				<c:when test="${contentType  eq 'image/jpeg' }">
					<img src="uploadFileSave/${fileName}" width="200px" height="200px" />
				</c:when>
				<c:otherwise>
					${fileName}<a href="fileDownload.do?fileName=${fileName}&originalFileName=${originalFileName}">다운로드</a>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	</table>
	
</center>
</body>
</html>















