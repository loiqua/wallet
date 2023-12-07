package com.walletstd22001.model;

import java.sql.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Accounts {
    private int accountId;
    private String accountName;
    private double balance;
    private Date lastUpdateDate;
    private List<Transaction> transactionList;
    private Currency currency;
    private String accountType;

    public Accounts performTransaction(int transactionId, String label, double amount, String transactionType) {
        Transaction newTransaction = new Transaction();
        newTransaction.setTransactionId(transactionId);
        newTransaction.setLabel(label);
        newTransaction.setAmount(amount);
        newTransaction.setDateTime(new Date(transactionId)); // Current date for the transaction
        newTransaction.setTransactionType(transactionType);

        if (accountType.equals("Bank") || (transactionType.equals("credit") && balance >= amount)) {
            if (transactionType.equals("debit")) {
                balance -= amount;
            } else {
                balance += amount;
            }
            transactionList.add(newTransaction);
            lastUpdateDate = new Date(transactionId); // Update account's date
        }

        return this;
    }
}
