package com.eBankSolution.eBank.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Type;
import java.util.Date;
import com.eBankSolution.eBank.Enum.*;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
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

    @Enumerated(EnumType.STRING)
    @Column(name="type" , nullable = false,length = 225)
    private TypeT type;

    @ManyToOne
    @JoinColumn(name ="compteId")
    private CompteBancaire compteB;
    @ManyToOne
    @JoinColumn(name = "beneficiaireId")
    private Beneficiaire beneficiaire;
//    @ManyToMany
//    private List<User> users;
}
