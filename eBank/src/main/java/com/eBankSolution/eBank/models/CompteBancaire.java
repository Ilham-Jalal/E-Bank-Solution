package com.eBankSolution.eBank.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CompteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer compteId;
    @Column
    private String typeCompte;
    @Column
    private Double solde;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateCreation;
    @Column
    private Integer numeroCompte;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @OneToMany(mappedBy = "compteB")
    private List<Beneficiaire> beneficiaire;
    @OneToMany(mappedBy = "compteB")
    private List<carteBancaire> carteBancaire;
    @OneToMany(mappedBy = "compteB" )
    private List<Transaction> Transaction ;
}
