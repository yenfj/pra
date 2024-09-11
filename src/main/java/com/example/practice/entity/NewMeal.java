package com.example.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "new_meal")
// @IdClass: 一個entity中若有多重PK，就需要生成一個class來管理多重PK
@IdClass(value = NewMealId.class)
public class NewMeal {
	@Id
	@Column(name = "name")
	private String name;

	@Id
	@Column(name = "cooking_style")
	private String cookingStyle;

	@Column(name = "price")
	private int price;

	public NewMeal() {
		super();
	}

	public NewMeal(String name, String cookingStyle, int price) {
		super();
		this.name = name;
		this.cookingStyle = cookingStyle;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getCookingStyle() {
		return cookingStyle;
	}

	public int getPrice() {
		return price;
	}
}
