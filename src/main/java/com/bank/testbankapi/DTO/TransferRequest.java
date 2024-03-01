package com.bank.testbankapi.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Tag(name = "Объект для перевода денег")
public class TransferRequest {

    @NotBlank
    @Schema(description = "Количество денег", example = "12.25")
    @Pattern(regexp = "[0-9]*\\.?[0-9]")
    private String amount;

    @NotBlank
    @Schema(description = "Имя человека для перевода", example = "Виктор")
    private String firstName;

    @NotBlank
    @Schema(description = "Фамилия человека для перевода", example = "Каб")
    private String lastName;

}
