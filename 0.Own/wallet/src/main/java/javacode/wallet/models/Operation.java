package javacode.wallet.models;

import lombok.Data;

import java.util.UUID;

@Data
public class Operation {
    private UUID walletId;
    private TypeOperation typeOperation;
    private Double amount;
}
