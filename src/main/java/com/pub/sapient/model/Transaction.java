package com.pub.sapient.model;

import java.util.Date;

public class Transaction {

	private String transactionId;

	private String clientId;

	private String securityId;

	private TransactionType transactionType;

	private Date transactionDate;

	private Double marketValue;

	private Boolean priorityFlag;

	private Double transactionFees;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Boolean getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(Boolean priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public Double getTransactionFees() {
		return transactionFees;
	}

	public void setTransactionFees(Double transactionFees) {
		this.transactionFees = transactionFees;
	}
}
