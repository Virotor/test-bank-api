package com.bank.testbankapi.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.Service.SearchService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "search")
@Tag(name = "API поиска")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    public ResponseEntity<String> message() {
        return ResponseEntity.ok("Hello world");
    }

    @GetMapping(path = "/birthDay")
    public ResponseEntity<?> getUserByBirthDay(@RequestParam(name = "birthday") String date,
            @RequestParam(name = "start_index", required = false, defaultValue = "0") Integer startPosition,
            @RequestParam(name = "count", required = false, defaultValue = "100") Integer count) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date birthDay = formatter.parse(date);
            return searchService.searchByBirthDay(birthDay, startPosition, count);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/phone")
    public ResponseEntity<?> getUserByPhone(@RequestParam(name = "phone") String phone) {
        try {
            return searchService.searchByPhone(phone);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "email") String email) {
        try {
            return searchService.searchByEmail(email);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/name")
    public ResponseEntity<?> getUserByEmail(@RequestParam(name = "firstName") String firstname,
            @RequestParam(name = "lastName") String lastname,
            @Parameter(description = "Стартовая позиция", name = "start_index", example = "10") @RequestParam(name = "start_index", required = false, defaultValue = "0") Integer startPosition,
            @Parameter(description = "Количество элементов", name = "count", example = "100") @RequestParam(name = "count", required = false, defaultValue = "100") Integer count) {
        try {
            return searchService.searchByFirstNameAndLastName(firstname, lastname, startPosition, count);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
