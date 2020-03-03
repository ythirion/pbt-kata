package com.bil.katas.pbt.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class Withdraw {
    private double amount;
    private Date requestDate;
}

