package com.example.practice.vo;

public class JoinVo {

	private String id;

	private String name;

	private int age;

	private String city;

	private int balance;

	public JoinVo() {
		super();
	}

	public JoinVo(String id, String name, int age, String city, int balance) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getCity() {
		return city;
	}

	public int getBalance() {
		return balance;
	}
}
