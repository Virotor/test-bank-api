package com.bank.testbankapi.Service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.DTO.TransferRequest;
import com.bank.testbankapi.Model.Account;
import com.bank.testbankapi.Repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public ResponseEntity<?> getUserAccount(String username) {
 
        String[] names = username.split("/");
        Optional<Account> account = accountRepository.findByUserFirstNameAndUserLastName(names[0], names[1]);
        if (account.isEmpty()) {
           return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем не существует"),
                    HttpStatus.BAD_REQUEST);

        }
        return ResponseEntity.ok(account.get());
    }

    public ResponseEntity<?> transferMoney(TransferRequest transferRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferMoney'");
    }





}
