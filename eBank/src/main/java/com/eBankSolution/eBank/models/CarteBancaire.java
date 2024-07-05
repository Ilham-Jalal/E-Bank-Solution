package com.eBankSolution.eBank.models;

import com.eBankSolution.eBank.Enum.Status;
import com.eBankSolution.eBank.Enum.TypeCarte;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CarteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carteId;
    @Column
    private String numero;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateExpiration;
    @Column
    @Enumerated(EnumType.STRING)
    private TypeCarte type;
    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false, length = 250)
    private Status status;

    @ManyToOne
    @JoinColumn(name= "compteId")
    private CompteBancaire compteB;


}
