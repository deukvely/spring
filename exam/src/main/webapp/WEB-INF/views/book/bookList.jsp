<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<table border="1">
	<thead>
		<tr>
			<th>도서번호</th>
			<th>도서명</th>
			<th>표지</th>
			<th>출판일자</th>
			<th>금액</th>
			<th>출판사</th>
			<th>도서소개</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${bookList}" var="book">
			<tr>
				<td>${book.bookNo }</td>
				<td>${book.bookName }</td>
				<td>${book.bookCoverimg }</td>
				<td><fmt:formatDate value="${book.bookDate }" pattern="yyyy/MM/dd"/></td>
				<td>${book.bookPrice }</td>
				<td>${book.bookPublisher }</td>
				<td>${book.bookInfo }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<script type="text/javascript">
	$('tbody > tr').on('click', function(e){
		let bookNo = $(e.currentTarget).find('td:nth-of-type(1)').text();
		location.href = 'bookInfo?bookNo='+bookNo;
	})
</script>
	

</body>
</html>