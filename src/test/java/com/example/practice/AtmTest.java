package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.service.ifs.AtmService;
import com.example.practice.vo.AddInfoReq;

@SpringBootTest
public class AtmTest {

	@Autowired
	AtmService atmService;
	
	@Test
	public void addInfoTest() {
		atmService.addInfo(new AddInfoReq("A01", "A01", 1000));
	}
}
