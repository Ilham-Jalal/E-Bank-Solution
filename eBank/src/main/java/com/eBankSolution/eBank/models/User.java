package com.eBankSolution.eBank.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String userName;
    @Column
    private String userPrenom;
    @Column
    private String userEmail;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<CompteBancaire> compteBancaire;

    public User(Integer userId, String userName, String userPrenom, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPrenom = userPrenom;
        this.userEmail = userEmail;
    }

    //    @ManyToMany
    //@JoinTable(
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
//    private List<Transaction> transaction;
}
