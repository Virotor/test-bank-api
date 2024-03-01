package com.bank.testbankapi.Service;

import java.util.Date;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.TypedSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.Model.User;
import com.bank.testbankapi.Repository.UserRepository;
import com.bank.testbankapi.Repository.UserSearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserSearchRepository userSearchRepository;

    public ResponseEntity<?> searchByBirthDay(Date date, Integer startPosition, Integer count) {
        TypedSort<User> user = Sort.sort(User.class);

        // Sort sort = user.by(User::getBirthDay).ascending()
        // .and(user.by(User::getLastName).ascending())
        // .and(user.by(User::getFirstName).ascending());

        Sort sort = user.by(User::getLastName).ascending()
                .and(user.by(User::getFirstName).ascending());

        Pageable page = PageRequest.of(startPosition, count, sort);

        return ResponseEntity.ok(userSearchRepository.findByBirthDayAfter(date, page));

    }

    public ResponseEntity<?> searchByPhone(String phone) {

        var result = userSearchRepository.findByPhonesValue(phone);
        if (result.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    String.format("Пользователь  с номером %s не найден", phone)), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result.get());
    }

    public ResponseEntity<?> searchByEmail(String email) {
        var result = userSearchRepository.findByEmailsValue(email);
        if (result.isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    String.format("Пользователь  с почтой %s не найден", email)), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result.get());
    }

    public ResponseEntity<?> searchByFirstNameAndLastName(String firstname, String lastname, Integer startPosition,
            Integer count) {
        TypedSort<User> user = Sort.sort(User.class);

        Sort sort = user.by(User::getLastName).ascending()

                .and(user.by(User::getFirstName).ascending());

        Pageable page = PageRequest.of(startPosition, count, sort);

        var res = userSearchRepository.findByFirstNameStartingWithAndLastNameStartingWith(firstname, lastname, page);
        return ResponseEntity.ok(res);
    }

}
