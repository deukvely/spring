package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// 전체조회
	public List<EmpVO> selectEmpAllList(EmpVO empVO);
	
	// 단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	// 등록
	public int insertEmpInfo(EmpVO empVO);
	
	// 수정 - 급여를 정해진 비율로 인상 // 두개 이상의 매개변수를 넘겨줄 때 파람 사용  // empId, raise로 사용
	public int updateEmpSal(@Param("empId") int employeeId, @Param("raise") int raise);
	
	// 수정 - 사원 정보 수정
	public int updateEmpInfo(EmpVO empVO);
	
	// 삭제
	public int deleteEmpInfo(int employeeId);
}
