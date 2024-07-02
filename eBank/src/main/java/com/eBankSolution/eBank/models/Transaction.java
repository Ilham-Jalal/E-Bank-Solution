package com.eBankSolution.eBank.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Type;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;
    @Column
    private Double montant;
    @Column
    private String description;
    @Column
    private String banque;
}
