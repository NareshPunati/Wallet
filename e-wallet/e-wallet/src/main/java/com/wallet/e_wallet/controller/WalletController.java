package com.wallet.e_wallet.controller;

import com.wallet.e_wallet.model.RequestModel;
import com.wallet.e_wallet.model.ResponseModel;
import com.wallet.e_wallet.model.BalanceResponseModel;
import com.wallet.e_wallet.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping("/topup")
    public ResponseModel topup(@Valid @RequestBody RequestModel requestModel) {
        return walletService.topup(requestModel.getUserId(), requestModel.getAmount());
    }

    @PostMapping("/deduct")
    public ResponseModel deduct(@RequestBody RequestModel requestModel) {
         return  walletService.deduct(requestModel.getUserId(), requestModel.getAmount());
    }

    @GetMapping("/balance")
    public BalanceResponseModel getBalance(@RequestParam String userId) {
        Float balance = walletService.getBalance(userId);
        return new BalanceResponseModel(balance);
    }
}
