package com.wallet.e_wallet.service;

import com.wallet.e_wallet.entity.Wallet;
import com.wallet.e_wallet.model.BalanceResponseModel;
import com.wallet.e_wallet.model.ResponseModel;
import com.wallet.e_wallet.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WalletService {

    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    WalletRepository walletRepository;

    @Transactional
    public synchronized ResponseModel topup(String userId, Float amount) {

            logger.info("Top-up request for user: {}, amount: {}", userId, amount);
            Wallet wallet = walletRepository.findByUserId(userId).orElse(new Wallet(userId, 0.0f));

            wallet.setBalance(wallet.getBalance() + amount);
            walletRepository.save(wallet);
            return new ResponseModel(true, wallet.getBalance(), UUID.randomUUID().toString());
    }

    @Transactional
    public synchronized ResponseModel deduct(String userId, Float amount) {

        logger.info("Deduct request for user: {}, amount: {}", userId, amount);
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new  RuntimeException("User Not Found"));

        if (wallet.getBalance() < amount) {

            logger.warn("Insufficient balance for user: {}, requested amount: {}", userId, amount);
            return  new ResponseModel(false, wallet.getBalance(), null);
        }
        wallet.setBalance(wallet.getBalance() - amount);
        walletRepository.save(wallet);
        return new ResponseModel(true, wallet.getBalance(), UUID.randomUUID().toString());
    }

    public Float getBalance(String userId) {

        logger.info("Balance request for user: {}", userId);
        return walletRepository.findByUserId(userId)
                .map(Wallet::getBalance)
                .orElse(0.0f);
    }
}
