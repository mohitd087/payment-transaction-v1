package com.app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Transaction_ID")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "Account_ID", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "Operationtype_ID", nullable = false)
    private OperationType operationType;

    @Column(name="Amount")
    private BigDecimal amount;

    @Column(name="EventDate")
    private LocalDateTime eventDate = LocalDateTime.now();

}
