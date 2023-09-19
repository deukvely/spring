package com.yedam.app.emp.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpMngController {
	
	@Autowired
	EmpService empService;
	
	// 조회(데이터, 일반페이지) -> GET
	// 등록, 수정, 삭제 -> POST (예외, 단건삭제 - GET)
	
	// 전체조회
	@GetMapping("empList")
	public String getEmpAllList(Model model) {
		model.addAttribute("empList", empService.getEmpAll());
		return "emp/empList";
	}
	
	// 단건조회
	@GetMapping("empInfo")
	public String getEmpInfo(EmpVO empVO, Model model) {
		model.addAttribute("empInfo", empService.getEmp(empVO));
		return "emp/empInfo";
	}
	
	// 등록 - Form
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/empInsert";
	}
	
	// 등록 - Process
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVo, RedirectAttributes attr) {
		int empId = empService.insertEmp(empVo);
		
		String result = null;
		if(empId == -1) {
			result = "정상적으로 등록되지 않았습니다.";

		}else {
			result = "정상적으로 등록되었습니다. \n등록된 사원의 사원번호는 " + empId + "입니다.";
		}
		attr.addAttribute("type", "insert"); // type은 호출 못함 변수가 아님
		attr.addFlashAttribute("result", result); // 얘는 변수로 호출할 수 있음
		// redirect는 컨트롤러를 호출하는 것, jsp file로 redirect 하지 않음
		return "redirect:empList?type=insert";
	}
	
	// 수정 - 1) 단건조회 -> 2) 수정
		
	// 수정 - Form
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.getEmp(empVO);
		model.addAttribute("empInfo", findVO);
		return "emp/empUpdate";
	}
	
	// 수정 - Process : ajax => 필수어노테이션, @RequestBody
	// @RequestBody - 보내주는 format json으로 고정함
	// ajax 입장에선 json이 필수는 아니지만 json 입장에선 ajax가 필수임
	@PostMapping("empUpdate")
	@ResponseBody
	public Map<String, String> empUpdateProcess(@RequestBody EmpVO empVO){
		
		return empService.updateEmp(empVO);
	}
	
	// 삭제 - 단건삭제 - ajax
	@GetMapping("empDelete") // 맵을 통째로 넘김
	@ResponseBody                           // 한 건 보내니깐 json으로 안 바꾸고 이런 방식도 있다.
	public Map<String, Object> empInfoDelete(@RequestParam Integer employeeId){ // Integer는 값이 없으면 null로 들어감
		List<Integer> list = new ArrayList<>(); // 리스트에 하나 담아서 보낸다.
		list.add(employeeId);
		return empService.deleteEmp(list); // 결과를 통째로 넘긴다
	}
	
	// 삭제 - 선택삭제(동시) : ajax // 배열을 처리하는 건 json(ajax)이 편함
	@PostMapping("empDelete")
	@ResponseBody
	public boolean empListDelete(@RequestBody List<Integer> empList){
		Map<String, Object> result = empService.deleteEmp(empList);
		return (boolean)result.get("result");
		// 성공 여부만 필요함 후속 작업을 할때는 전체를 넘겨주던가 
	}
}
