<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="insertForm" action="bookInsert" method="post">
		<table border="1">
			<tr>
				<th>도서번호</th>
				<td><input type="text" name="bookNo" value="노력하겠습니당:)" readonly></td>
			</tr>
			<tr>
				<th>도서명</th>
				<td><input type="text" name="bookName"></td>
			</tr>
			<tr>
				<th>도서표지</th>
				<td><input type="text" name="bookCoverimg"></td>	
			</tr>
			<tr>
				<th>출판일자</th>
				<td><input type="text" name="bookDate"></td>	
			</tr>
			<tr>
				<th>금액</th>
				<td><input type="text" name="bookPrice"></td>	
			</tr>
			<tr>
				<th>출판사</th>
				<td><input type="text" name="bookPublisher"></td>	
			</tr>
			<tr>
				<th>도서소개</th>
				<td><textarea  name="bookInfo" rows="" cols=""></textarea></td>
			</tr>
		</table>
	<button type="submit">등록</button>
	<button type="button" onclick="location.href='bookList'">목록</button>
	</form>
</body>
</html>