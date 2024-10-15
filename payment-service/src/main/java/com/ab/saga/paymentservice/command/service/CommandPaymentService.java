package com.ab.saga.paymentservice.command.service;

import com.ab.commonapi.commands.CreateUserBalanceCommand;
import com.ab.saga.paymentservice.command.dto.UserBalanceRequestDto;
import com.ab.saga.paymentservice.command.dto.UserBalanceResponseDto;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@Service
public class CommandPaymentService {

    private final CommandGateway commandGateway;

    public CompletableFuture<UserBalanceResponseDto> createUserBalance(UserBalanceRequestDto requestDto) {
        // Validate the initial balance
        BigDecimal initialBalance = requestDto.getInitialBalance();
        if (initialBalance == null) {
            throw new IllegalArgumentException("Initial balance cannot be null");
        }
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        // Create and send the command
        String userId = UUID.randomUUID().toString();
        CreateUserBalanceCommand command = new CreateUserBalanceCommand(userId, initialBalance);

        // Process the command and create the response DTO
        return commandGateway.send(command).thenApply(result ->
                new UserBalanceResponseDto("User balance created successfully.", userId, initialBalance)
        );
    }
}
