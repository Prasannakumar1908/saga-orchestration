package com.ab.saga.paymentservice.command.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBalanceResponseDto {
    private String message; // A message indicating success or error
    private String userId;  // The ID of the user
    private BigDecimal initialBalance; // The initial balance created
}
