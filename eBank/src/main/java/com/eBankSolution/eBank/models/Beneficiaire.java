package com.eBankSolution.eBank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer beneficiaireId;
    @Column
    private String beneficiaireName;
    @Column
    private Integer numeroCompte;
    @Column
    private String banque;
    @ManyToOne
    @JoinColumn(name="compteId")
    private CompteBancaire compteB;
    @OneToMany(mappedBy = "beneficiaire")
    @JsonIgnore
    private List<Transaction> transaction;
}

