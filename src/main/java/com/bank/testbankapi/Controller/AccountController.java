package com.bank.testbankapi.Controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.testbankapi.DTO.JwtResponse;
import com.bank.testbankapi.DTO.TransferRequest;
import com.bank.testbankapi.Service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Управление счётом пользователя")
public class AccountController {

    private final AccountService accountService;

    // @GetMapping(path = "/message")
    // public ResponseEntity<?> getMessage() {
    // var s = SecurityContextHolder.getContext().getAuthentication();
    // log.debug(s.toString());
    // return ResponseEntity.ok("Hello world ");
    // }

    // @GetMapping("/getInfo")
    // @Schema(description = "Получение информации о счёте пользователя")
    // public ResponseEntity<?> getUserAccount() {
    // var username =
    // SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    // log.debug(username);
    // return accountService.getUserAccount(username);
    // }

    @Schema(description = "Перевод денег со счёта пользователя на счёт другого пользователя")
    @Operation(summary = "Перевод денег")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Транзакция прошла успешно"),
            @ApiResponse(responseCode = "400", description = "Ошибка в работе")
    })
    @PostMapping("/transferMoney")
    public ResponseEntity<?> transferMoney(@RequestBody @Valid TransferRequest transferRequest) {
        var username = Stream
                .of(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().split("/"))
                .collect(Collectors.toList());

        return accountService.transferMoney(transferRequest, username);
    }

}
