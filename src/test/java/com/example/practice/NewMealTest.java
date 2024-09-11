package com.example.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.entity.NewMeal;
import com.example.practice.service.ifs.NewMealService;

@SpringBootTest
public class NewMealTest {
	@Autowired
	private NewMealService newMealService;
	
	@Test
	public void addTest() {
		newMealService.add(new NewMeal("½¼", "¶B", 160));
	}
	
	@Test
	public void findTest() {
		newMealService.findMeal("³½", "²M»]");
	}
}
