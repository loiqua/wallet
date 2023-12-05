package com.walletstd22001.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Currency {
    private long currencyId; // Utilisation de camelCase pour suivre les conventions Java
    private String currencyName;
}
