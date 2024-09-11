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

	// @validation: �y�k��m�b�̤W��
	// @NotBlank(message = ""): �P�_1.�Dnull 2. �Ŧr�� 3. �ťզr��
	@NotBlank(message = "-account error-")
	@Id
	@Column(name = "account")
	private String acc;
	
	// @JsonProperty("password"): �i�H�N�~���ШD��key�M�A�������r��mapping�A�ñN�Ƚᤩ���ܼƤ��A#���u�঳�@��#
	// @JsonAlias({"password", "pwd"}): �ĪG�P@JsonProperty�A�i�H���h��key��mapping
	// �G�ܤ@
//	@JsonProperty("password")
	@JsonAlias({ "password", "pwd" })
	@NotBlank(message = "-password error-")
	@Column(name = "password")
	private String pwd;

	// @Min(value = x, message = ""): �P�_��J��O�_�j�󵥩�x(�̧C����min��x)
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
