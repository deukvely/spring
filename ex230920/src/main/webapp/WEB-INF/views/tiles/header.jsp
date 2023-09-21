<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="nav justify-content-center">
		<li class="nav-item">
			<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath }/">Home</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="${pageContext.request.contextPath }/boardList">전체조회</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="${pageContext.request.contextPath }/boardInsert">등록</a>
		</li>
		<li class="nav-item">
			<a class="nav-link disabled" aria-disabled="true">Disabled</a>
		</li>
	</ul>
</body>
</html>