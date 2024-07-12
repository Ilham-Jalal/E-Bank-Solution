package com.eBankSolution.eBank.controllers.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {
        private String username;
        private String password;
}