package javacode.wallet.controllers;

import javacode.wallet.models.Wallet;
import javacode.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping
    public List<Wallet> getAllWallets() {
        return walletService.findAll();
    }

    @GetMapping("/{id}")
    public Wallet getById(@PathVariable UUID id) {
        return walletService.findByUuid(id);
    }

    @PostMapping
    public Wallet create(@RequestBody Wallet wallet) {
        return walletService.create(wallet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        walletService.deleteById(id);
    }
}