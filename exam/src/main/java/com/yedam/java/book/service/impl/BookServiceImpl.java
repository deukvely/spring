package com.yedam.java.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.java.book.mapper.BookMapper;
import com.yedam.java.book.service.BookService;
import com.yedam.java.book.service.BookVO;
@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookMapper bookMapper;
	
	@Override
	public List<BookVO> getBookList() {
		List<BookVO> findList = bookMapper.getBookList();
		return findList;
	}

	@Override
	public BookVO getBookInfo(BookVO bookVO) {
		return bookMapper.getBook(bookVO);
	}

	@Override
	public int insertBookInfo(BookVO bookVO) {
		int result = bookMapper.insertBook(bookVO);
		
		if(result == 1) {
			return bookVO.getBookNo();
		} else {
			return -1;
		}
	}

	@Override
	public int updateBookInfo(BookVO bookVO) {
		return 0;
	}

	@Override
	public int deleteBookInfo(int bookNo) {
		return 0;
	}

}
