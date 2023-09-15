package com.yedam.app.emp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpMapper empMapper;
	
	// get : 조회, 빈 페이지 요청
	// 그 외(post, delete, put) : 등록, 수정, 삭제
	
	// @RequestMapping(value="empForm", method=RequestMethod.GET)
	@GetMapping("empForm") // 같은 방식
	public void getEmpInfoForm() {}
	
	@GetMapping("getEmp")   // model은 돌려줄 때 사용
	public String getEmpData(EmpVO empVO, Model model) {
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "empForm";
	}
								
	@GetMapping("getEmpData")   // model은 돌려줄 때 사용
	public String getEmpData(@RequestParam(defaultValue = "100" // @RequestParam은 객체가 아닌 기본타입, String과 같이 단일값
										, name = "eId"			// defaultValue은 데이터가 없을 때 기본값 // name은 파라미터 이름
										, required=true) Integer employeeId, Model model) { // required은 필수값이냐 선택값이냐 반드시 있어야 됨
												// false인 경우 사용자가 데이터를 보낼 때 있어야 되는지 없어도 되는지 false인 경우 null이 들어간다.
												// false인 경우 데이터가 없어 null을 넘겨준다, 기본타입은 안 되고 integer wrapperclass
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "empForm";
	}
	
	@GetMapping("emp/{id}")
	public String getEmpData2(@PathVariable(name = "id") Integer employeeId, Model model) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		model.addAttribute("empInfo", findVO);
		return "empForm";
	}
	
	
}
