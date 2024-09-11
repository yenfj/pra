package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.entity.NewMeal;
import com.example.practice.entity.NewMealId;

@Repository
public interface NewMealDAO extends JpaRepository<NewMeal, NewMealId> {
	// 因為NewMeal是多重PK，則需透過一個class管理這些PK
	// NewMealId即為管理NewMeal中多重PK屬性的class，所以: JpaRepository<NewMeal, NewMealId>
	public void findByName(String name);

	public void findByCookingStyle(String cookingStyle);

	public NewMeal findByNameAndCookingStyle(String name, String cookingStyle);
}
