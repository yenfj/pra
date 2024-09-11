package com.example.practice.service.ifs;

import com.example.practice.entity.NewMeal;

public interface NewMealService {
	
	public void add(NewMeal newMeal);

	public void findMeal(String name, String cookingStyle);
}
