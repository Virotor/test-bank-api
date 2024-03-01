package com.bank.testbankapi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.testbankapi.DTO.RegistrationRequest;
import com.bank.testbankapi.Service.RegistrationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
@Tag(name = "Регистрация пользователя")
public class RegistrationController {

    final private RegistrationService registrationService;

    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Пользователь создан"),
            @ApiResponse(responseCode = "400", description = "Ошибка в создании пользователя")
    })
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return registrationService.registrationUser(registrationRequest);
    }

}
