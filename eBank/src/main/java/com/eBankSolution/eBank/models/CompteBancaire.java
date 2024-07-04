package com.eBankSolution.eBank.models;

import com.eBankSolution.eBank.Enum.TypeC;
import com.eBankSolution.eBank.Enum.TypeT;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Enumerated(EnumType.STRING)
    @Column(name="type" , nullable = false,length = 225)
    private TypeC type;

    @Column
    private Double solde;

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateCreation;
    @Column
    private Boolean accountClossed = false;
    @Column
    private String RaisonClosing;

    @Column
    private Integer numeroCompte;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "compteB")
    @JsonIgnore
    private List<Beneficiaire> beneficiaire;

    @OneToMany(mappedBy = "compteB")
    @JsonIgnore
    private List<carteBancaire> carteBancaire;

    @OneToMany(mappedBy = "compteB" )
    @JsonIgnore
    private List<Transaction> Transaction ;
}
