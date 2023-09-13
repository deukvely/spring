package com.yedam.app.java;

import org.springframework.stereotype.Component;


public class SamsungTV implements TV{

	@Override
	public void on() {
		System.out.println("삼성 TV를 켰습니다.");
	}
}
