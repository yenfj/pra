package com.example.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.entity.Meal;

@Repository // 將此介面交由Spring Boot託管成repository類
public interface MealDAO extends JpaRepository<Meal, String> {
	// Meal: MealDAO要操作的class
	// String: 在class Meal()的屬性中，有加丄@Id(為key鍵)的屬性之資料型態(在這只有單一屬性為key)
	
	/*
	 * 因為不寫SQL語法，而是透過JPA的方式來操作資料庫，所以自訂的DAO會有以下規則:
	 * 1. 撈取資料: findBy(xxx(And(xxx)，(xxx)是entity中class(Meal)的屬性變數名稱(不是Column中的欄位名稱)
	 * 2. findBy(xxx(And(xxx)(): xxx須是大駝峰寫法，即使屬性變數名稱是小駝峰，ex: findByNameAndPrice()
	 * 3. (xxx(And(xxx)And(xxx)...(): 有幾個屬性變數名稱，()中就要有幾個變數
	 */
	public Meal findByNameAndPrice(String name, int price);
	
	public Meal findByName(String name);
	
	/*
	 * 1. 查資料是否存在(return T/F)，關鍵字: exists
	 * 2. 自定義DAO方法: By後面的名稱須為屬性變數名稱
	 * 3. 自定義DAO方法中之(): 若參數為多筆的話，方法名稱要機上In(師否有在查詢裡面的意思)
	 * 4. All可略
	 */
	public boolean existsAllByNameIn(List<String> nameList);
}
