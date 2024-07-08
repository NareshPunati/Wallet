package com.wallet.e_wallet.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.wallet.e_wallet.model.ResponseModel;
import com.wallet.e_wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletServiceTests {

    @Autowired
    private WalletService walletService;

    @Test
    public void testTopup() {
        ResponseModel response = walletService.topup("user1", 100.0f);
        assertTrue(response.isStatus());
        assertEquals(100.0f, response.getNewBalance(), 0.0f);
    }

    @Test
    public void testDeduct() {
        walletService.topup("user2", 100.0f);
        ResponseModel response = walletService.deduct("user2", 50.0f);
        assertTrue(response.isStatus());
        assertEquals(50.0f, response.getNewBalance(), 0.0f);
    }

    @Test
    public void testGetBalance() {
        walletService.topup("user3", 200.0f);
        Float balance = walletService.getBalance("user3");
        assertEquals(200.0f, balance, 0.0f);
    }
}
