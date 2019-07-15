package com.pub.sapient.fee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import com.pub.sapient.model.Transaction;
import com.pub.sapient.model.TransactionFee;
import com.pub.sapient.model.TransactionType;

public class FeeCalculator {

    private static final Logger LOGGER = Logger.getLogger(FeeCalculator.class.getName());

    private List<Transaction> transactionList = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactionList.add(calculateFee(transaction));
    }

    public void addTransaction(List<Transaction> transactions) {
        transactions.forEach(this::addTransaction);
        //Consumer<Transaction> s = this::addTransaction;
    }

    private Transaction calculateFee(Transaction transaction) {
        if (isIntradayTransaction(transaction)) {
            transaction.setTransactionFees(TransactionFee.TEN.getFees());
        } else {
            if (transaction.getPriorityFlag()) {
                transaction.setTransactionFees(TransactionFee.FIVE_HUNDRED.getFees());
            } else {
                if (transaction.getTransactionType() == TransactionType.SELL
                        || transaction.getTransactionType() == TransactionType.WITHDRAW) {
                    transaction.setTransactionFees(TransactionFee.HUNDRED.getFees());
                } else if (transaction.getTransactionType() == TransactionType.BUY
                        || transaction.getTransactionType() == TransactionType.DEPOSIT) {
                    transaction.setTransactionFees(TransactionFee.FIFTY.getFees());
                }
            }
        }
        return transaction;
    }

    private boolean isIntradayTransaction(Transaction other) {
        boolean isIntraDayTransaction = false;
        if (transactionList.size() > 0) {
            for (Transaction transaction : transactionList) {
                if (transaction.getClientId().equals(other.getClientId())
                        && transaction.getSecurityId().equals(other.getSecurityId())
                        && transaction.getTransactionDate().equals(other.getTransactionDate())) {
                    if ((transaction.getTransactionType() == TransactionType.BUY
                            && other.getTransactionType() == TransactionType.SELL)
                            || (transaction.getTransactionType() == TransactionType.SELL
                            && other.getTransactionType() == TransactionType.BUY)) {
                        isIntraDayTransaction = true;
                        break;
                    }
                }
            }
        }
        return isIntraDayTransaction;
    }

    public void displayTransactionReport() {
        LOGGER.info("Printing report");
        transactionList.sort(Comparator.comparing(Transaction::getClientId)
                .thenComparing(Transaction::getTransactionType)
                .thenComparing(Transaction::getTransactionDate)
                .thenComparing(Transaction::getPriorityFlag));
        System.out.println("Fee Report for Sales");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Client Id | Transaction Type | Transaction Date | Priority | Processing Fee    |");
        for (Transaction transaction : transactionList) {

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println(transaction.getClientId() + "\t| " + transaction.getTransactionType().value() + "\t| " +
                    transaction.getTransactionDate() + "\t| " + (transaction.getPriorityFlag() ? "Y\t" : "N") + "\t| " +
                    transaction.getTransactionFees() + "\t|");
        }
        System.out.println("--------------------------------------------------------------------------------");
    }
}
