package com.bank.testbankapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Данные для смены почты")
public class EmailRequest {

    @Schema(description = "Старая почта пользователя", example = "example@test.com")
    @NotBlank
    @Email
    private String oldEmail;

    @Schema(description = "Новая почта пользователя", example = "example@test.com")
    @NotBlank
    @Email
    private String newEmail;
}
