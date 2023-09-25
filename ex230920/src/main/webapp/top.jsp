<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<sec:csrfMetaTags/>
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1> 톱 페이지입니다.</h1>
	<ul>
		<li><a href="user/user.jsp">일반 사용자용 페이지로</a></li>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li><a href="admin/admin.jsp">관리자 전용 페이지로</a></li>
		</sec:authorize>
	</ul>
	<sec:authorize access="isAuthenticated()">
		<form action="logout" method="post">
			<sec:csrfInput/>
			<button>로그아웃</button>
		</form>
	</sec:authorize>
	<script type="text/javascript">
		$.ajax('boardUpdate',{
			type: 'post',
			contentType: 'application/json',
			data: JSON.stringify({'bno' : '002'}),
			// 데이터로 보내기 위해선 beforeSend를 해야지 가능함
			beforeSend : function(xhr){ // dom이 가지고 있는 속성 프로퍼티, 자신이 가지고 있는 속성 애트리뷰트
				let header = $('meta[name="_csrf_header"]').attr('content');
				let token = $('meta[name="_csrf"]').attr('content');
				
				xhr.setRequestHeader(header, token);
			}
		})
		.done(result => console.log(result))
		.fail(resect => console.log(resect));
	</script>
</body>
</html>