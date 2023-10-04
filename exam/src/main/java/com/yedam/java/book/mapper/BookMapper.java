package com.yedam.java.book.mapper;

import java.util.List;

import com.yedam.java.book.service.BookVO;

public interface BookMapper {
	// 전체 조회
	public List<BookVO> selectBookAll();
	
	// 단건조회 -> 북넘버 자동부여 : 예측
	public Integer selectBookNo();
	
	// 등록
	public int insertBookInfo(BookVO bookVO);
	
	
}
