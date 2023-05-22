package org.anastasia.libraryadministration.authservice.component;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
@RequiredArgsConstructor
@Component
public class JwtBuilder {
    @Value("${jwt.expirationMinutes}")
    private int expirationMinutes;
    private final SecretKey key;
    public String buildJwt(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusMinutes(expirationMinutes)))
                .signWith(key)
                .compact();
    }
}
