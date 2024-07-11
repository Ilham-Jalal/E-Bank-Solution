package com.eBankSolution.eBank.controllers.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class LoginRequest{
        @Id
        private Long id;
        String username;
        String password;



}