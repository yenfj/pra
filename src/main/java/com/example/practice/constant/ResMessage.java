package com.example.practice.constant;

public enum ResMessage {

	SUCCESS(200, "-success-"), //
	ACC_ERROR(400, "-account error-"), //
	PWD_ERROR(400, "-password error-"), //
	BAL_ERROR(400, "-balance error-"), //
	INSUFFICIENT_BAL(400, "-insufficient balance-"), //
	AMOUNT_ERROR(400, "-amount error-"), //
	ACC_EXISTED(400, "-account existed-"), //
	ACC_NOT_FOUND(404, "-account not found-"), //
	UPDATE_FAILED(400, "-update failed-");

	private int code;

	private String message;

	private ResMessage(int code, String message) {
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
