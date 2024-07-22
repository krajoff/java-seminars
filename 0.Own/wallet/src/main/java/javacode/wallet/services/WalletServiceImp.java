package javacode.wallet.services;

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
    public Wallet findByUuid(UUID uuid) {
        return walletRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    @Override
    public Wallet create(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet update(UUID uuid, Wallet wallet) {
        Wallet existingWallet = findByUuid(uuid);
        existingWallet.setBalance(wallet.getBalance());
        return walletRepository.save(existingWallet);
    }

    @Override
    public void deleteById(UUID uuid) {
        walletRepository.deleteById(uuid);
    }
}
