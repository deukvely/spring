package com.yedam.java.rent.service;

import java.util.Date;

import lombok.Data;

@Data
public class RentVO {
	private Integer rentNo;
	private Integer bookNo;
	private Integer rentPrice;
	private Date rentDate;
	private Integer rentStatus;
}
