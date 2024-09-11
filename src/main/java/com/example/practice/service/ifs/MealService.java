package com.example.practice.service.ifs;

import java.util.List;

import com.example.practice.entity.Meal;

public interface MealService {
	public void addMeal(Meal meal);
	
	public void updatePrice(Meal meal);
	
	public void findByName(String name);

	public void addMeals(List<Meal> meal);
	
	public void allMeal();
}
