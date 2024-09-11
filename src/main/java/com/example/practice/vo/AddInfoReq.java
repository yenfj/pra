package com.example.practice.vo;

import com.example.practice.entity.Atm;

public class AddInfoReq extends Atm {

	public AddInfoReq() {
		super();
	}

	public AddInfoReq(String acc, String pwd, int balance) {
		super(acc, pwd, balance);
	}

}
