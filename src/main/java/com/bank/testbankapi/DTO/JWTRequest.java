package com.bank.testbankapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Tag(name  = "Запрос на аутентификацию")

public record JWTRequest(
        @Schema(description = "Имя пользователя", example = "Jon") @NotBlank(message = "Имя пользователя не может быть пустыми") String firstName,
        @Schema(description = "Имя пользователя", example = "Jon") @NotBlank(message = "Имя пользователя не может быть пустыми") String lastName,
        @Schema(description = "Пароль", example = "my_1secret1_password") @Size(min = 8, max = 255, message = "Длина пароля должна быть от 8 до 255 символов") @NotBlank(message = "Пароль не может быть пустыми") String password)

{

}
