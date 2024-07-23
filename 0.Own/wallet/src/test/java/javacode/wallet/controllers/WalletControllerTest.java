package javacode.wallet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacode.wallet.models.Operation;
import javacode.wallet.models.TypeOperation;
import javacode.wallet.models.Wallet;
import javacode.wallet.services.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletService walletService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID walletId;
    private Wallet wallet;
    private Operation operation;

    @BeforeEach
    public void setup() {
        wallet = new Wallet();
        walletId = UUID.randomUUID();
        wallet.setId(walletId);
        wallet.setBalance(100d);

        operation = new Operation();
        operation.setTypeOperation(TypeOperation.DEPOSIT);
        operation.setWalletId(walletId);
        operation.setAmount(10d);

        walletService.create(wallet);
    }

    @Test
    public void testGetById_Success() throws Exception {
        given(walletService.findByUuid(walletId)).willReturn(wallet);

        mockMvc.perform(get("/api/v1/wallet/{walletId}", walletId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(walletId.toString()))
                .andExpect(jsonPath("$.balance").value(100.0));
    }

    @Test
    public void testGetById_NotFound() throws Exception {
        UUID walletId = UUID.randomUUID();
        given(walletService.findByUuid(walletId))
                .willThrow(new RuntimeException("Wallet not found"));

        mockMvc.perform(get("/api/v1/wallet/{walletId}", walletId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.message").value("Wallet not found"));
    }

    @Test
    public void testOperation_Success() throws Exception {
        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operation)))
                .andExpect(status().isOk());
    }

    @Test
    public void testOperation_BadRequest() throws Exception {
        Operation operation = new Operation(null, null, 0.0);
        Mockito.doThrow(new RuntimeException("Wrong request"))
                .when(walletService).operate(Mockito.any(Operation.class));

        mockMvc.perform(post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operation)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value("Wrong request"));
    }


}
