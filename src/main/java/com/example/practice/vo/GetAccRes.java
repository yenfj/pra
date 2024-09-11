package com.example.practice.vo;

import java.util.List;

public class GetAccRes extends AddInfoRes {

	private List<String> accList;

	public GetAccRes() {
		super();
	}

	public GetAccRes(int code, String message, List<String> accList) {
		super(code, message);
		this.accList = accList;
	}
	public List<String> getAccList() {
		return accList;
	}

}
