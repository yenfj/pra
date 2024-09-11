package com.example.practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practice.entity.NewMeal;
import com.example.practice.entity.NewMealId;

@Repository
public interface NewMealDAO extends JpaRepository<NewMeal, NewMealId> {
	// �]��NewMeal�O�h��PK�A�h�ݳz�L�@��class�޲z�o��PK
	// NewMealId�Y���޲zNewMeal���h��PK�ݩʪ�class�A�ҥH: JpaRepository<NewMeal, NewMealId>
	public void findByName(String name);

	public void findByCookingStyle(String cookingStyle);

	public NewMeal findByNameAndCookingStyle(String name, String cookingStyle);
}
