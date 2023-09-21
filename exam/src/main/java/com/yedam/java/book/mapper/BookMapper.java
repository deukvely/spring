package com.yedam.java.book.mapper;

import java.util.List;

import com.yedam.java.book.service.BookVO;

public interface BookMapper {
	public List<BookVO> getBookList();
	public BookVO getBook(BookVO bookVO);
	public int insertBook(BookVO bookVO);
	public int updateBook(BookVO bookVO);
	public int deleteBook(int bookNo);
}
