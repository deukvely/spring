package com.yedam.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;
// db를 설정하는 건 root-context, 웹을 처리하는 건 servlet-context
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmpMapperTest {
	@Autowired
	EmpMapper empMapper;
	
	//@Test
	public void selectAllEmp() {
		// 전체 조회
		List<EmpVO> empList = empMapper.selectEmpAllList(null);
		assertNotNull(empList);
	}
	
	//@Test
	public void selectEmpInfo() {
		// 단건 조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		// 널을 체크하지 않음 정확히 검색되었는지가 중요
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");
	}
	//@Test
	public void insertEmpInfo() {
		// 등록
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Kang");
		empVO.setFirstName("Na-La");
		empVO.setEmail("nlKang@google.com");
		empVO.setJobId("IT_PROG");
		
		// insert 담을 변수는 없다.
		// empVO에 들어간 변수 4개 그래도 사용된 프라이머리키를 가지고 올 수 있다
		empMapper.insertEmpInfo(empVO);
		//assertNotEquals(empVO.getEmployeeId(), 0);
	}
	
	//@Test
	public void updateEmpSal() {
		// 급여 갱신
		int result = empMapper.updateEmpSal(100, 10);
		assertEquals(result, 1);
	}
	
	//@Test
	public void updateEmpInfo() {
		// 사원 정보 수정
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(207);
		
		empVO.setEmail("kang@naver.com");
		//empVO.setSalary(6000);
		
		int result = empMapper.updateEmpInfo(empVO);
		assertEquals(result, 1);
	}
	
	@Test
	public void deleteEmpInfo() {
		// 사원 삭제
		int result = empMapper.deleteEmpInfo(207);
		assertEquals(result, 1);
	}
	
	
}
