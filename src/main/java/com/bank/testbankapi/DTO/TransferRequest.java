package com.bank.testbankapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @JsonProperty
    @NotBlank
    private String amount;
    @JsonProperty
    @NotBlank
    private String firstName;
    @JsonProperty
    @NotBlank
    private String lastName;

}
