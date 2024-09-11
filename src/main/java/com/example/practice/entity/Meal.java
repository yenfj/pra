package com.example.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // �N�����O���Spring Boot�U�ަ�entity��
@Table(name = "meal") // �Nclass Meal()���p����w��#��ƪ�W��meal#
public class Meal {
	
	@Id // �������쬰PK(key��)
	@Column(name = "name") // �N�ܼ����p����w��#��ƪ����#(name = "���W��")
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
