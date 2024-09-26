package javacode.wallet.services;

import javacode.wallet.models.Wallet;
import javacode.wallet.repositories.WalletRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WalletServiceImpTest {
    private static final UUID uuid = UUID.randomUUID();

    @Mock
    private WalletRepository walletRepository;

    @InjectMocks
    private WalletServiceImp walletService;

    @Test
    void findWallet_shouldCallRepository() {
        final Wallet wallet = mock(Wallet.class);
        when(walletRepository.findById(uuid)).thenReturn(Optional.ofNullable(wallet));

        final Wallet actual = walletService.findByUuid(uuid);

        assertNotNull(actual);
        assertEquals(wallet,actual);
        verify(walletRepository).findById(uuid);
    }

    @Test
    void saveWallet_shouldCallRepository(){
        final Wallet wallet = mock(Wallet.class);

        walletService.create(wallet);

        verify(walletRepository).save(wallet);
    }
}
