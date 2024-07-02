package com.eBankSolution.eBank.models;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Beneficiaire")
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
}
