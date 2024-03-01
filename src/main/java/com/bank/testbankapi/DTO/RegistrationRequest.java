package com.bank.testbankapi.DTO;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Данные для регистарции нового пользователя")
public class RegistrationRequest {

    @NotBlank
    @Schema(description = "Имя пользователя", example = "Витя")
    @Size(min = 6, max = 255, message = "Длина имени должна быть от 2 до 255 символов")
    private String firstName;

    @NotBlank
    @Schema(description = "Фамилия пользователя", example = "Каба")
    @Size(min = 6, max = 255, message = "Длина фамилии должна быть от 2 до 255 символов")
    private String lastName;

    @NotNull
    @Past
    @Schema(description = "Дата рождения", example = "2024-03-01")
    private Date birhDay;

    @NotNull
    @Schema(description = "Пароль", example = "secret")
    @Size(min = 6, max = 255, message = "Длина пароля должна быть от 6 до 255 символов")
    private String password;

    @NotBlank
    @Schema(description = "Номер телефона", example = "+375440000000")
    @Pattern(regexp = "/+375(44|46|29|33|25)\\d{7}/")
    private String phone;

    @NotNull
    @Email
    @Schema(description = "Почта пользователя", example = "example@test.com")
    private String email;

    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE - 1)
    @Schema(description = "Стартовый капитал пользователя", example = "100")
    private BigDecimal amount;

}
