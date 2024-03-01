package com.bank.testbankapi.Service;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.DTO.RegistrationRequest;
import com.bank.testbankapi.Model.Account;
import com.bank.testbankapi.Model.Email;
import com.bank.testbankapi.Model.Phone;
import com.bank.testbankapi.Model.User;
import com.bank.testbankapi.Repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final ContactService contactService;
    private final AccountRepository accountRepository;

    public ResponseEntity<?> registrationUser(RegistrationRequest registrationRequest) {

        if (userService
                .findByFirstNameAndLastName(registrationRequest.getFirstName(), registrationRequest.getLastName())
                .isPresent()) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"),
                    HttpStatus.BAD_REQUEST);
        }
        if (contactService.isUsedEmail(registrationRequest.getEmail())) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным email уже существует"),
                    HttpStatus.BAD_REQUEST);
        }
        if (contactService.isUsedEmail(registrationRequest.getPhone())) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.BAD_REQUEST.value(),
                            "Пользователь с указанным номером телефона уже существует"),
                    HttpStatus.BAD_REQUEST);
        }

        Email email = new Email();
        email.setValue(registrationRequest.getEmail());
        email = contactService.saveEmail(email);

        Phone phone = new Phone();
        phone.setValue(registrationRequest.getPhone());
        phone = contactService.savePhone(phone);

        User user = new User();
        user.setBirthDay(registrationRequest.getBirhDay());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmails(Set.of(email));
        user.setPhones(Set.of(phone));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

        user = userService.createUser(user);

        Account account = new Account();
        account.setAmount(registrationRequest.getAmount());
        account.setUser(user);
        account = accountRepository.save(account);

        return ResponseEntity.ok("User create");
    }

}
