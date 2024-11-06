package com.example.shoppingcart.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secretKey;
    
    @Value("${jwt.expiration}")
    private long expiration;
    
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
    
    public Claims extractClaims(String token) {
        JwtParser jwtParser = Jwts.parser()
                .setSigningKey(secretKey)
                .build();
        
        return jwtParser.parseClaimsJws(token).getBody();
    }
    
    public String getUsername(String token) {
        return extractClaims(token).getSubject();
    }
    
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
    
    public boolean validateToken(String token, String username) {
        return (username.equals(getUsername(token)) && !isTokenExpired(token));
    }
}

