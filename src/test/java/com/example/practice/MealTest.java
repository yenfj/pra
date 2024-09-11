package com.example.practice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.practice.entity.Meal;
import com.example.practice.service.ifs.MealService;

// #���դ�k��#�Y���ϥΨ�"@Autowired"�A�N�n�[�W��Annotation @SpringBootTest(test�M��)
// ��Annotation�|�A�]���դ�k�e�A�������ӱM��(�qmain��k�i�J����)�A�ëإߩҦ��ӰU�ު�����
@SpringBootTest
public class MealTest {
	@Autowired // �I�s�QSpring Boot�U�ު�����: Spring Boot�|�N�Q�U�ު�����`�J���ܼƤ�
	private MealService mealService;
	
	@Test
	public void addMealTest() {
		mealService.addMeal(new Meal("����", 180));
	}
	
	@Test
	public void addMealsTest() {
		List<Meal> meal1 = List.of(new Meal(" ", 180),new Meal("����", 180),new Meal("�F��", 80),new Meal("����", 160),new Meal("�ޱ�", 120),new Meal("����", -10),new Meal("�F��", 75));
		mealService.addMeals(meal1);
		List<Meal> meal2 = List.of(new Meal("�F��", 75),new Meal("����", 140),new Meal("�ޱ�", 110));
		mealService.addMeals(meal2);
	}
	
	@Test
	public void updatePriceTest() {
		mealService.updatePrice(new Meal("����", 220));
	}
	
	@Test
	public void findByNameTest() {
		mealService.findByName("����");
	}
	
	@Test
	public void getMealTest() {
//		Meal meal = mealDao.findByName("aaa");
//		System.out.printf("%s: %d\n", meal.getName(), meal.getPrice());
		
		mealService.allMeal();
	}
}
