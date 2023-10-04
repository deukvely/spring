package com.yedam.java.book.service;

import lombok.Data;

@Data
public class RentVO {
	private String bookNo;
	private String bookName;
	private Integer rentPrice;
	private Integer rentstatus;
}
