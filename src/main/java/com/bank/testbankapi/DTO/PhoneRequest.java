package com.bank.testbankapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Запрос на изменение номера телефона")
public class PhoneRequest {

    @Schema(description = "Старый номер телефона", example = "+375440000000")
    @NotBlank
    @Pattern(regexp = "/+375(44|46|29|33|25)\\d{7}/")
    private String oldPhone;

    @Schema(description = "Новый номер телефона", example = "+375440000001")
    @NotBlank
    @Pattern(regexp = "/+375(44|46|29|33|25)\\d{7}/")
    private String newPhone;
}
