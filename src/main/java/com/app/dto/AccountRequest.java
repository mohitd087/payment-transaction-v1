package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class AccountRequest {

    @NotBlank(message = "Document number is required")
    @Pattern(regexp = "\\d+", message = "Document number must be numeric")
    @JsonProperty("document_number")
    private String documentNumber;
}
