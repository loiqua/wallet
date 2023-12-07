package com.walletstd22001.model;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {
    private int transactionId;
    private String label;
    private double amount;
    private Date dateTime;
    private String transactionType;
}