package com.pub.sapient.model;

public enum TransactionType {
	BUY("BUY"), SELL("SELL"), DEPOSIT("DEPOSIT"), WITHDRAW("WITHDRAW");

	private final String value;

	TransactionType(String val) {
		value = val;

	}

	public String value() {
		return value;
	}

}
