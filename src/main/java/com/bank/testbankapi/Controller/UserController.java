package com.bank.testbankapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.testbankapi.DTO.AppError;
import com.bank.testbankapi.DTO.EmailRequest;
import com.bank.testbankapi.DTO.PhoneRequest;
import com.bank.testbankapi.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "user")
@RequiredArgsConstructor
@Tag(name = "Управлением аккаунтом")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получение данных пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @GetMapping("/getInfo")
    public ResponseEntity<?> getUserInfo() {

        try {
            return ResponseEntity.ok(userService.getUserByToken());
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Изменение почты пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @PostMapping("/updateEmail")
    public ResponseEntity<?> updateUserEmail(@RequestBody EmailRequest emailRequest) {
        try {
            return userService.updateUserEmail(emailRequest.getOldEmail(), emailRequest.getNewEmail());
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Измнение номера телефона пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @PostMapping("/updatePhone")
    public ResponseEntity<?> updateUserPhone(@RequestBody PhoneRequest phoneRequest) {
        try {
            return userService.updateUserPhone(phoneRequest.getOldPhone(), phoneRequest.getNewPhone());
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Operation(summary = "Добавление телефона пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @PostMapping("/addPhone")
    public ResponseEntity<?> addPhone(@RequestBody String addingPhone) {
        try {
            return userService.addPhone(addingPhone);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Добавление почты пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @PostMapping("/addEmail")
    public ResponseEntity<?> addEmail(@RequestBody String addingEmail) {
        try {
            return userService.addEmail(addingEmail);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Удаление  почты пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @DeleteMapping("/deleteEmail")
    public ResponseEntity<?> deleteEmail(@RequestBody String deleteEmail) {
        try {
            return userService.deleteEmail(deleteEmail);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Operation(summary = "Удаление  номера пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
    })
    @DeleteMapping("/deletePhone")
    public ResponseEntity<?> deletePhone(@RequestBody String deletePhone) {
        try {
            return userService.deletePhone(deletePhone);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage()),
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
