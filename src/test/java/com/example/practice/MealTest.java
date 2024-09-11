package com.example.practice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.entity.Meal;
import com.example.practice.service.ifs.MealService;

// #測試方法中#若有使用到"@Autowired"，就要加上此Annotation @SpringBootTest(test專用)
// 此Annotation會再跑測試方法前，先執行整個專案(從main方法進入執行)，並建立所有該託管的物件
@SpringBootTest
public class MealTest {
	@Autowired // 呼叫被Spring Boot託管的物件: Spring Boot會將被託管的物件注入到變數中
	private MealService mealService;
	
	@Test
	public void addMealTest() {
		mealService.addMeal(new Meal("牛排", 180));
	}
	
	@Test
	public void addMealsTest() {
		List<Meal> meal1 = List.of(new Meal(" ", 180),new Meal("牛排", 180),new Meal("沙拉", 80),new Meal("雞排", 160),new Meal("豬排", 120),new Meal("魚排", -10),new Meal("沙拉", 75));
		mealService.addMeals(meal1);
		List<Meal> meal2 = List.of(new Meal("沙拉", 75),new Meal("雞排", 140),new Meal("豬排", 110));
		mealService.addMeals(meal2);
	}
	
	@Test
	public void updatePriceTest() {
		mealService.updatePrice(new Meal("牛排", 220));
	}
	
	@Test
	public void findByNameTest() {
		mealService.findByName("牛排");
	}
	
	@Test
	public void getMealTest() {
//		Meal meal = mealDao.findByName("aaa");
//		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
		
		mealService.allMeal();
	}
}
