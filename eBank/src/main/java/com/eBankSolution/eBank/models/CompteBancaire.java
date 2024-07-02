package com.eBankSolution.eBank.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "CompteBancaire")
public class CompteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer compteId;
    @Column
    private String typeCompte;
    @Column
    private Double solde;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateCreation;
    @Column
    private Integer numeroCompte;
}
