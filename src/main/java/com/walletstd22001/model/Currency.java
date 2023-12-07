package com.walletstd22001.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Currency {
    private int currencyId;
    private String name;
    private String code;
}
