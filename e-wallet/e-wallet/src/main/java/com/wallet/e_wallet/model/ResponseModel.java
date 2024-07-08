package com.wallet.e_wallet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseModel {

    private boolean status;
    private Float newBalance;
    private String transactionId;

}
