package com.example.practice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAlias;
//import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "atm")
public class Atm {

	// @validation: 粂猭竚程よ
	// @NotBlank(message = ""): 耞1.獶null 2. ﹃ 3. フ﹃
	@NotBlank(message = "-account error-")
	@Id
	@Column(name = "account")
	private String acc;
	
	// @JsonProperty("password"): 盢场叫―いkey㎝珹腹い﹃mapping盢结ぉ跑计い#Τ#
	// @JsonAlias({"password", "pwd"}): 狦@JsonPropertyΤkeymapping
	// 拒
//	@JsonProperty("password")
	@JsonAlias({ "password", "pwd" })
	@NotBlank(message = "-password error-")
	@Column(name = "password")
	private String pwd;

	// @Min(value = x, message = ""): 耞块借琌单x(程minx)
	@Min(value = 0, message = "-balance error-")
	@Column(name = "balance")
	private int balance;

	public Atm() {
		super();
	}

	public Atm(String acc, String pwd, int balance) {
		super();
		this.acc = acc;
		this.pwd = pwd;
		this.balance = balance;
	}

	public String getAcc() {
		return acc;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}
