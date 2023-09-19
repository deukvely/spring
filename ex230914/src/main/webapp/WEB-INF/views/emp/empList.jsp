<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 사원 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<p>${result }</p>
	<button type="button">선택삭제</button>
	<table border="1">
		<thead>
			<tr>
				<th>Check</th>
				<th>employee_id</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>hire_date</th>
				<th>job_id</th>
				<th>salary</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${empList }" var="emp">
				<tr>
					<td><input type="checkbox"></td>
					<td>${emp.employeeId }</td>
					<td>${emp.firstName }</td>
					<td>${emp.lastName }</td>
					<td>${emp.email }</td>
					<td><fmt:formatDate value="${emp.hireDate }" pattern="yyyy-MM-dd"/></td>
					<td>${emp.jobId }</td>
					<td>${emp.salary }</td>
					<td><button type="button">Del</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script type="text/javascript">
		//let message = [
		//	<c:forEach begin="1" end="5">
		//		`${result}`,
		//	</c:forEach>
		//]
		//if (message.length > 0) alert(message.toString());
		
		$('tbody > tr').on('click', function(e){
			if(e.target.tagName != 'TD') return; // 이벤트가 실제 발생한 타겟 // 발생한 td가 아닐 경우 밑에 실행문을 실행하지 않음
						// tagName 태그이름은 대문자로 하여야 인식할 수 있음
						
			//let empId = e.currentTarget.firstElementChild.nextElementSibling.textContent;
			let empId = $(e.currentTarget).find('td:nth-of-type(2)').text(); // = td:eq(1) 
			location.href = 'empInfo?employeeId='+empId;
		});
		
		// 단건삭제
		$('tr button').on('click', empInfoDel);
		
		function empInfoDel(event){
			let trTag = event.currentTarget.closest('tr');
			//let trTag = $(event.currentTarget).closest('tr'); / jqeury
			let empId = $(trTag).children().eq(1).text(); // 버튼에서 employeeId 찾기 위해서 tr로 올라가서 td 두번째로 감
			
			$.ajax('empDelete?employeeId='+empId)
			.done(result => {
				// console.log(result);
				let deletedId = result.list[0];
				// td:eq(1) eq는 인덱스로 첫번째 값만 가지고 옴
				$('tbody > tr > td:nth-of-type(2)').each(function(idx, tag){
					if(tag.textContent == deletedId){
						$(tag).parent().remove();
					}
				})
			})
			.fail(reject => console.log(reject));
		}
		// 공부하는 방법 이론 공부보단 만들어본 거 뜯어보기 코드 하나하나 이해하기*****
		// 지금까지 한 것을 설명할 수 있나??? 모르겠다면 한줄 한줄부터 이해하고 넓게 보기*****
		// 선택삭제
		$('button:eq(0)').on('click', empListDelete);
		
		function empListDelete(event){
			// 선택한 사원번호를 가지는 배열
			let empIdList = getEmpList(); // 메소드 호출을 하는 건 왼쪽에 받아줄 변수가 있다면 메소드에는 리턴이 있어야 된다.
			
			// ajax
			$.ajax('empDelete',{ // RequestBody를 하면 이 형태로 간다 달라지는 건 변수명// 외워도 된다**
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(empIdList)
			})
			.done(result => { // 배열을 기반으로 보내야 할 때 하나의 방법 // 이 코드 쓰면 한 소리 먹긴 할 거임
				// console.log(result);
				if(result){
					location.href='empList';
				}
			})
			.fail(reject => console.log(reject));
		}
		
		function getEmpList(){ // checkbox에는 값이 없다. 중요한 건 employeeId의 값을 가지고 와야 된다.
			let checkTag = $('tbody input[type="checkbox"]:checked'); // 대괄호는 속성검색할 때 씀
			
			let empList = [];
			checkTag.each(function(idx, inTag){
				let empId = $(inTag).parent().next().text();
				empList.push(empId);
			}); // 반복문이 계속 도니깐 push가 계속 될 수도 있다.
			
			return empList;
		}
		
		
		
		
		
		
		
		
		
	</script>
</body>
</html>