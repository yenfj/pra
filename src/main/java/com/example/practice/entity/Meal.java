package com.example.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // 將此類別交由Spring Boot託管成entity類
@Table(name = "meal") // 將class Meal()關聯到指定的#資料表名稱meal#
public class Meal {
	
	@Id // 表明此欄位為PK(key鍵)
	@Column(name = "name") // 將變數關聯到指定的#資料表欄位#(name = "欄位名稱")
	private String name;
	
	@Column(name = "price")
	private int price;

	public Meal() {
		super();
	}

	public Meal(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
}
