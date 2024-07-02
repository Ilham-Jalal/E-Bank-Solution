package com.eBankSolution.eBank.models;

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
@Table(name = "carteBancaire")
public class carteBancaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carteId;
    @Column
    private Integer numero;
    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateExpiration;
    @Column
    private String type;
}
