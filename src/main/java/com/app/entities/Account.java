package com.app.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Data
public class Account {

    @Id
    @Column(name="Account_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    @Column(name="Document_Number",unique = true, nullable = false)
    private String documentNumber;
}
