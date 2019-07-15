package com.pub.sapient.model;

public enum TransactionFee {
	FIVE_HUNDRED(500), HUNDRED(100), FIFTY(50), TEN(10);
	private double fees;

	TransactionFee(double fees) {
        this.fees = fees;
    }

	public double getFees() {
		return fees;
	}
}
