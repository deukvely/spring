<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 수정</title>
</head>
<body>
	<form>
		<div>
			<label>employee_id : <input type="number" name="employeeId" value="${empInfo.employeeId }" readonly></label>
		</div>
		<div>
			<label>first_name : <input type="text" name="firstName" value="${empInfo.firstName }"></label>
		</div>
		<div>
			<label>last_name : <input type="text" name="lastName" value="${empInfo.lastName }" readonly></label>
		</div>
		<div>
			<label>email : <input type="email" name="email" value="${empInfo.email }"></label>
		</div>
		<div>
			<label>hire_date : <input type="date" name="hireDate" value="${empInfo.hireDate }" readonly></label>
		</div>
		<div>
			<label>salary : <input type="number" name="salary" value="${empInfo.salary }"></label>
		</div>
		<button type="button">수정</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>