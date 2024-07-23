package javacode.wallet.controllers;

import javacode.wallet.models.Error;
import javacode.wallet.models.Operation;
import javacode.wallet.models.Wallet;
import javacode.wallet.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{walletId}")
    public ResponseEntity<?> getById(@PathVariable UUID walletId) {
        try {
            return new ResponseEntity<>(walletService.findByUuid(walletId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Error(404, "Wallet not found"),
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> operation(@RequestBody Operation operation) {
        try {
            walletService.operate(operation);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Error(400, "Wrong request"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Wallet wallet) {
        try {
            walletService.create(wallet);
            return new ResponseEntity<>(wallet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Error(400, "Wrong request"),
                    HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{walletId}")
    public ResponseEntity<?> delete(@PathVariable UUID walletId) {
        try {
            walletService.deleteById(walletId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new Error(404, "Wallet not found"),
                    HttpStatus.NOT_FOUND);
        }
    }
}