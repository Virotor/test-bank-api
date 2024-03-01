package com.bank.testbankapi.Controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.testbankapi.DTO.TransferRequest;
import com.bank.testbankapi.Service.AccountService;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Управление счётом пользователя")
public class AccountController {

    private final AccountService accountService;

    @GetMapping(path = "/message")
    public ResponseEntity<?> getMessage() {
        var s = SecurityContextHolder.getContext().getAuthentication();
        log.debug(s.toString());
        return ResponseEntity.ok("Hello world ");
    }

    @GetMapping("/getInfo")
    @Schema(description = "Получение информации о счёте пользователя")
    public ResponseEntity<?> getUserAccount() {
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.debug(username);
        return accountService.getUserAccount(username);
    }

    @PostMapping("/transferMoney")
    @Schema(description = "Перевод денег со счёта пользователя на счёт другого пользователя")
    public ResponseEntity<?> transferMoney(@RequestBody TransferRequest transferRequest) {
        var username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.debug(username);
        return accountService.transferMoney(transferRequest);
    }

}
