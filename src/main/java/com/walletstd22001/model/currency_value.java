package com.walletstd22001.model;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class currency_value {
    private UUID idcurrencyvalue;
    private Currency sourceCurrency;
    private Currency destinationCurrency;
    private double conversionRate;
    private Date dateEffect;
}
