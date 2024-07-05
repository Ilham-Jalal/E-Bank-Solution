package com.eBankSolution.eBank.models;

import com.eBankSolution.eBank.Enum.TypeC;
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
    private Date dateCreation;
    @Column
    private Boolean accountClossed = false;
    @Column
    private String RaisonClosing;

    @Column
    private String numeroCompte;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "compteB")
    @JsonIgnore
    private List<Beneficiaire> beneficiaire;

    @OneToMany(mappedBy = "compteB")
    @JsonIgnore
    private List<CarteBancaire> carteBancaires = new ArrayList<>();

    @OneToMany(mappedBy = "compteB",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> Transaction ;


}
