package com.example.practice.vo;

public class GetInfoRes extends AddInfoRes {
	
	private String acc;
	
	private int balance;

	public GetInfoRes() {
		super();
	}

	public GetInfoRes(int code, String message) {
		super(code, message);
	}
	
	public GetInfoRes(int code, String message, String acc, int balance) {
		super(code, message);
		this.acc = acc;
		this.balance = balance;
	}

	public String getAcc() {
		return acc;
	}

	public int getBalance() {
		return balance;
	}
}
