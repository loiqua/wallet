package com.walletstd22001.model;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    public void addTransaction(Transaction transaction) {
        // Implementation of the addTransaction method
    }

    public Accounts performTransaction(int transactionId, String label, double amount, String transactionType) {
        // Implementation of the performTransaction method
        return this;
    }

    public double getBalanceAtDateTime(Date dateTime) {
        // Implementation of the getBalanceAtDateTime method
        return 0.0;
    }

    public double getCurrentBalance() {
        // Implementation of the getCurrentBalance method
        return 0.0;
    }

    public List<Double> getBalanceHistory(Date startDate, Date endDate) {
        // Implementation of the getBalanceHistory method
        return null;
    }
}
