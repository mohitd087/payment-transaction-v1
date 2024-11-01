package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {

    @NotNull(message = "Account ID is required")
    @JsonProperty("account_id")
    private Long accountId;

    @NotNull(message = "Operation Type ID is required")
    @JsonProperty("operation_type_id")
    private Long operationTypeId;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

}
