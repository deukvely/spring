<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th><!-- bno -->
				<th>제목</th><!-- title -->
				<th>작성자</th><!-- writer -->
				<th>작성일</th><!-- yyyy년MM월dd일 -->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList }" var="board">
				<tr>
					<td>${board.bno }</td>
					<td>${board.title }</td>
					<td>${board.writer }</td>
					<td><fmt:formatDate value="${board.regdate }" pattern="yyyy년MM월dd일"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		document.querySelectorAll('tbody > tr')
			.forEach(function(tag){
				tag.addEventListener('click', function(e){
					let bno = e.currentTarget.firstElementChild.textContent;
					
					location.href='boardInfo?bno=' + bno;
				});
			});
// 		$('tbody > tr').on('click', function(e){
// 			let bno = $(e.currentTarget).find('td:nth-of-type(1)').text();
// 			location.href = 'boardInfo?bno=' + bno;
// 		})
	</script>
</body>
</html>