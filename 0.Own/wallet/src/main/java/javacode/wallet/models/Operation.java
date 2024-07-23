package javacode.wallet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation {
    private UUID walletId;
    private TypeOperation typeOperation;
    private Double amount;
}
