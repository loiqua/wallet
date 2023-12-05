package com.walletstd22001.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {
    private long transactionId; // Utilisation de camelCase pour suivre les conventions Java
    private LocalDate transactionDate;
    private BigDecimal amount;
    private String description;
    private long accountId; // Utilisation de camelCase pour suivre les conventions Java
}