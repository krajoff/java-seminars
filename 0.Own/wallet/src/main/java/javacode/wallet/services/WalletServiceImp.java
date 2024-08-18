package javacode.wallet.services;

import javacode.wallet.models.Operation;
import javacode.wallet.models.TypeOperation;
import javacode.wallet.models.Wallet;
import javacode.wallet.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WalletServiceImp implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet findByUuid(UUID id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public Wallet create(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet operate(Operation operation) {
        Wallet wallet = findByUuid(operation.getWalletId());
        Double amount = operation.getAmount();
        if (wallet != null && amount > 0) {
            if (operation.getTypeOperation().equals(TypeOperation.DEPOSIT)) {
                wallet.setBalance(wallet.getBalance() + amount);
                return update(wallet.getId(), wallet);
            }
            if (operation.getTypeOperation().equals(TypeOperation.WITHDRAW) &&
                    wallet.getBalance() - amount > 0) {
                wallet.setBalance(wallet.getBalance() - amount);
                return update(wallet.getId(), wallet);
            } else throw new RuntimeException("Not enough money for withdraw");
        } else throw new RuntimeException("Wrong operation");
    }

    @Override
    public Wallet update(UUID id, Wallet wallet) {
        Wallet existingWallet = findByUuid(id);
        existingWallet.setBalance(wallet.getBalance());
        return walletRepository.save(existingWallet);
    }

    @Override
    public void deleteById(UUID id) {
        walletRepository.deleteById(id);
    }
}
