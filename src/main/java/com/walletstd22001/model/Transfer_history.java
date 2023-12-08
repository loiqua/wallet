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
public class Transfer_history {
    private UUID idTransfer;
    private Transaction debitTransaction;
    private Transaction creditTransaction;
    private Date transferDate;
}
