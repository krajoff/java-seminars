package javacode.wallet.services;

import javacode.wallet.models.Operation;
import javacode.wallet.models.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    List<Wallet> findAll();

    Wallet findByUuid(UUID id);

    Wallet create(Wallet wallet);

    Wallet operate(Operation operation);

    Wallet update(UUID id, Wallet wallet);

    void deleteById(UUID id);
}
