package com.eBankSolution.eBank.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtHelper {


    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int EXPIRATION_MINUTES = 10;

    public static String generateToken(String username) {
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_MINUTES * 60 * 1000);
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }
}
