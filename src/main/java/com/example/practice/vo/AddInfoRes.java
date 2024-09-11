package com.example.practice.vo;

public class AddInfoRes {
	private int code;

	private String message;

	public AddInfoRes() {
		super();
	}

	public AddInfoRes(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
