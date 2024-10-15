package com.ab.saga.paymentservice.command.controller;

import com.ab.saga.paymentservice.command.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.command.dto.UserBalanceResponseDto;
import com.ab.saga.paymentservice.command.service.CommandPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Tag(name = "Payment Balance Command Controller")
@AllArgsConstructor
@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final CommandPaymentService commandPaymentService;

    @Operation(summary = "Create new user balance")
    @PostMapping
    public CompletableFuture<ResponseEntity<UserBalanceResponseDto>> createUserBalance(@RequestBody UserBalanceRequestDto requestDto) {
        return commandPaymentService.createUserBalance(requestDto)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> {
                    // Handle the exception and return an appropriate response
                    return ResponseEntity.badRequest().body(new UserBalanceResponseDto(ex.getMessage(), null, null));
                });
    }
}
