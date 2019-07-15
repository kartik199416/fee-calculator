package com.pub.sapient.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pub.sapient.model.Transaction;
import com.pub.sapient.model.TransactionType;

public class Utility {

	public static Transaction getTransaction(String[] transactionAttributes) {

		return getTransaction(transactionAttributes[0], transactionAttributes[1], transactionAttributes[2],
				transactionAttributes[3], transactionAttributes[4], transactionAttributes[5], transactionAttributes[6]);

	}

	public static Transaction getTransaction(String transactionId, String clientId, String securityId,
			String transactionType, String transactionDate, String marketValue, String priority) {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(transactionId);
		transaction.setClientId(clientId);
		transaction.setSecurityId(securityId);
		transaction.setTransactionType(Utility.parseTransactionType(transactionType));
		transaction.setTransactionDate(Utility.parseDate(transactionDate));
		transaction.setMarketValue(Utility.parseMarketValue(marketValue));
		transaction.setPriorityFlag(Utility.getPriority(priority));
		return transaction;

	}

	public static Double parseMarketValue(String marketValue) {
		try {
			return Double.parseDouble(marketValue);
		} catch (Exception ignored) {
			return (double) 0;
		}
	}

	public static Boolean getPriority(String priority) {
		if (priority != null) {
			priority = priority.trim();
			return priority.equals("Y") || priority.equals("y");
		} else {
			return false;
		}
	}

	public static Date parseDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date convertedCurrentDate = sdf.parse(date);
			return convertedCurrentDate;
		} catch (Exception ignored) {
			return null;
		}
	}

	public static TransactionType parseTransactionType(String type) {
		switch (type) {
		case "BUY":
			return TransactionType.BUY;
		case "SELL":
			return TransactionType.SELL;
		case "DEPOSIT":
			return TransactionType.DEPOSIT;
		case "WITHDRAW":
			return TransactionType.WITHDRAW;
		}
		return null;
	}

}
