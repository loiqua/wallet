package com.walletstd22001.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Accounts {
    private long id_account;
    private String name_account;
    private BigDecimal current_balance;
    private long currency_id;

    public void setId_account(long id_account) {
        this.id_account = id_account;
    }

    public void setName_account(String name_account) {
        this.name_account = name_account;
    }

    public void setCurrent_balance(BigDecimal current_balance) {
        if (current_balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance must be greater than or equal to 0");
        }
        this.current_balance = current_balance;
    }

    public void setCurrency_id(long currency_id) {
        this.currency_id = currency_id;
    }
}
