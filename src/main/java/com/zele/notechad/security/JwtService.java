package com.zele.notechad.security;

import com.zele.notechad.tools.SecretKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@Slf4j
public class JwtService {
    private final SecretKey secretKey = SecretKeyGenerator.generateKey();

    public String generateToken(String username) {
        log.info("Generating token for user: {}", username);
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (1000*60*3))) // sets session to 3 minutes, pretty sure this is what providus bank uses mtchew
                .signWith(secretKey)
                .compact();
    }

    private Claims getClaimsFromToken(String token) {
        log.info("Getting claims from token: {}", token);
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) {
        log.info("Getting username from token: {}", token);
        return getClaimsFromToken(token).getSubject();
    }

    private Date getExpiry(String token) {
        log.info("Getting expiry from token: {}", token);
        return getClaimsFromToken(token).getExpiration();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        log.info("Validating token: {} and {}", userDetails.getUsername(), getUsernameFromToken(token));
        if (getUsernameFromToken(token).equals(userDetails.getUsername()) && getExpiry(token).after(new Date())) {
            log.info("Token Validated");
            return true;
        }
        log.error("Token Invalidated");
        return false;
    }
}