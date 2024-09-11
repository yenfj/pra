package com.example.practice.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AtmReq2 {

	@NotBlank(message = "-account error-")
	private String acc;

	@NotBlank(message = "-password error-")
	private String pwd;

	@Min(value = 0, message = "-amount error-")
	private int amount;
	
	private boolean deposit;

	public AtmReq2() {
		super();
	}

	public AtmReq2(String acc, String pwd, int amount, boolean deposit) {
		super();
		this.acc = acc;
		this.pwd = pwd;
		this.amount = amount;
		this.deposit = deposit;
	}

	public String getAcc() {
		return acc;
	}

	public String getPwd() {
		return pwd;
	}

	public int getAmount() {
		return amount;
	}

	public boolean getDeposit() {
		return deposit;
	}
}
