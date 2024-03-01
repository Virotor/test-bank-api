package com.bank.testbankapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Пользовательские ошибки")
public record AppError(

        @Schema(description = "Код ошибки", example = "404") Integer code,
        @Schema(description = "Описание ошибки", example = "Пользователь не найден") String message) {

}
