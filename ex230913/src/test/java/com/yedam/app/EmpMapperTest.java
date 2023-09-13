package com.yedam.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@RunWith(SpringJUnit4ClassRunner.class)
										// 물리적으로 찾아가게 만듬
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmpMapperTest {
	// 구현 클래스는 따로 없음, 임의로 만들어야 됨
	@Autowired
	EmpMapper empMapper;
	
	@Test
	public void getEmp() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId("100");
		
		EmpVO findVO = empMapper.getEmp(empVO);
		
		assertEquals(findVO.getLastName(), "King");
	}
	
}
