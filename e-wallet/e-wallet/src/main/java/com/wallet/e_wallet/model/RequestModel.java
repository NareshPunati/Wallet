package com.wallet.e_wallet.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModel {

    @NotNull(message = "User ID cannot be null")
    @NotEmpty
    private String userId;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be positive")
    private Float amount;

}
