<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div>
		<form action="getEmp" method="get">
			<label>사원번호<input type="number" name="employeeId"></label>
			<button type="submit">검색</button>
		</form>
	</div>
	<div>
		<table>
			<tr>
				<th>사원번호</th>
				<td>${empInfo.employeeId }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${empInfo.firstName }</td>
			</tr>
			<tr>
				<th>업무</th>
				<td>${empInfo.jobId }</td>
			</tr>
		</table>
	</div>
	<hr>
	<form action="">
		<table>
			<tr>
				<th>성</th>
				<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th>입사일</th>
				<td><input type="text" name="hireDate"></td>
			</tr>
			<tr>
				<th>업무</th>
				<td><input type="text" name="jobId"></td>
			</tr>
		</table>
		<button type="submit">등록</button>
	</form>
	<script>
		// form이랑 ajax는 정반대의 성질 
		// form은 데이터를 통합하는 기능 때문에 같이 씀
		$('form:eq(1)').on('submit', function(e){ // 이벤트 객체
			e.preventDefault(); 
			// let formData = new Formdata(document.getElementsByTagName('form')[1]); JS 방식
			let formData = $('form:eq(1)').serializeArray(); // serialize : 결과가 리턴되는게 queryString으로 변환됨 key=value&key=value..
			// serializeArray : 배열이라서 결과도 배열로 나옴 // 객체의 배열 [ { name : ' ', value " ' ' } , ]
			
			formData = $('table input'); // 큰 속성 안에 있는 건 다 들고 오고 serializeArray() key&value만 골라서 들고 옴
			
			let formObj = {}; // 뭐 어떻게 들고 오든 이건 안 변함
			$.each(formData, function(idx, obj){
				// serializeArray 이것은 배열이고 객체만 빼오는 작업을 해야 됨 { name : ' ', value " ' ' }
				// 하나의 객체 => 하나의 필드
				formObj[obj.name] = obj.value;
			});
			
			console.log(formData, formObj);
			
			$.ajax('empInfoInsert', {
				type : 'post',
				contentType : 'application/json', // 통신 방법은 정해져있음
				data : JSON.stringify(formObj)
			})
			.done(data =>{
				console.log(data);
			})
			.fail(reject => console.log(reject));
			
			// return false;
		});
		
		/*
			Event Object
			- 메소드
			1) preventDefault() : 해당 이벤트에 기본으로 설정된 동작을 막음 // 중첩하는 태그가 많을 때 적절히 사용
			2) stopPropagation() : 이벤트 버블링을 막음 // 버블링 하위요소가 타고 올라가는 것
			
			- 필드
			1) target : 정말 이벤트가 발생한 애(고정값), 사용자가 선택한 거임
			2) currentTarget : 현재 이벤트 핸들러가 동작하는 태그(변통값) => this // 버블링이 되면서 올라가면서 찾아감
		*/
	</script>
</body>
</html>