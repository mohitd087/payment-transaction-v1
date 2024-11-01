package com.app.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OperationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Operationtype_ID")
    private Long operationTypeId;

    private String description;

}
