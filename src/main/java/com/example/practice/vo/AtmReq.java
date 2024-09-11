package com.example.practice.vo;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.practice.entity.PersonInfo;

public class AtmReq {

	@NotBlank(message = "-account error-")
	private String acc;

	@NotBlank(message = "-password error-")
	private String pwd;

	@Min(value = 0, message = "-amount error-")
	private int amount;

	// @Valid: 嵌套驗證，會向下驗證"id是否為空字串"
	@Valid
	@NotNull(message = "-personInfo cannot be null-")
	private PersonInfo personInfo;

	public AtmReq() {
		super();
	}

	public AtmReq(String acc, String pwd, int amount) {
		super();
		this.acc = acc;
		this.pwd = pwd;
		this.amount = amount;
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

	public PersonInfo getpersonInfo() {
		return personInfo;
	}

	public void setpersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

}
