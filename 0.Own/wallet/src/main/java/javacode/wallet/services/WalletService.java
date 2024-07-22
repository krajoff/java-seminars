package javacode.wallet.services;

import javacode.wallet.models.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    List<Wallet> findAll();

    Wallet findByUuid(UUID uuid);

    Wallet create(Wallet wallet);

    Wallet update(UUID uuid, Wallet wallet);

    void deleteById(UUID uuid);
}
