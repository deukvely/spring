package com.yedam.app.java.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	// bean으로 등록해서 끌고 들어옴 bean이 주입되는 위치다.
	@Autowired
	Chef chef;
	
	@Autowired
	TV tv;
	
	public void open() {
		chef.cooking();
	}
}
