package com.yedam.java.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.java.book.service.BookService;
import com.yedam.java.book.service.BookVO;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.getBookList();
		model.addAttribute("bookList", list);
		return "book/bookList";
	}
	
	@GetMapping("bookInfo")
	public String bookInfo(BookVO bookVO, Model model) {
		model.addAttribute("bookInfo", bookService.getBookInfo(bookVO));
		return "book/bookInfo";
	}
	
	@GetMapping("bookInsert")
	public String bookInsertForm() {
		return "book/bookInsert";
	}
	
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		bookService.insertBookInfo(bookVO);
		return "redirect:bookList";
	}
}
