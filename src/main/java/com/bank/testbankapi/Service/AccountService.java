package com.bank.testbankapi.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.DTO.TransferRequest;
import com.bank.testbankapi.Model.Account;
import com.bank.testbankapi.Repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    public Account getUserAccount(List<String> names) throws Exception {
        Optional<Account> account = accountRepository.findByUserFirstNameAndUserLastName(names.get(0), names.get(1));
        if (account.isEmpty()) {
           throw new Exception("Пользователь с указанным именем не существует");
        }
        return account.get();
    }

    public ResponseEntity<?> transferMoney(TransferRequest transferRequest, List<String> username) {
        try {
            log.debug("Проверка суммы транзакции");
            if(new BigDecimal(transferRequest.getAmount()).compareTo(new BigDecimal(0))<-1){
                log.debug("Всё плохо");
                throw new Exception("Неверная сумма перевода");
            }
            log.debug("Получение счёта на который совершается перевод");
            Account accountToTransfer = getUserAccount(List.of(transferRequest.getFirstName(), transferRequest.getLastName()));
            log.debug("Получение счёта с которого осущевствяется перевод");
            Account accountFromTransfer = getUserAccount(username);
            log.debug("Перевод денег");
            accountRepository.transferMoney(accountFromTransfer.getId(), accountToTransfer.getId(),new BigDecimal(transferRequest.getAmount()));
            return ResponseEntity.ok("Транзакция выполнена успешно");
            // if (result.equals(1)) {
            //     log.debug("Успешный перевод");
            //     return ResponseEntity.ok("Транзакция выполнена успешно");
            // } else {
            //     log.debug("Что-то пошло не так");
            //     return ResponseEntity.ok("Ошибка выполнения транзакции");
            // }
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
