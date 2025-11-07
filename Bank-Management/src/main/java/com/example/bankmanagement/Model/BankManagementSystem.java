package com.example.bankmanagement.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankManagementSystem {
    private int id;
    private String Username;
    private double Balance;
}
