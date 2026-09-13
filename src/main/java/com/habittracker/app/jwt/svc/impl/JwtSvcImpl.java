package com.habittracker.app.jwt.svc.impl;

import com.habittracker.app.config.JwtConfig;
import com.habittracker.app.jwt.svc.iface.JwtSvc;
import com.habittracker.app.user.data.dto.response.UserDetailsResponse;
import com.habittracker.app.user.data.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.MacAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtSvcImpl implements JwtSvc {

    private final JwtConfig jwtConfig;

    @Override
    public String getToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()
                + jwtConfig.getExpiraionTime());
        SecretKey key = getSignWithKey(jwtConfig.getSecret());
        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .expiration(expiryDate)
                .issuedAt(new Date())
                .signWith(key, Jwts.SIG.HS256)
                .subject(user.getId())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .compact();
    }

    @Override
    public Claims validateToken(String token) {
        return Jwts.parser()
                .verifyWith(getSignWithKey(jwtConfig.getSecret()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignWithKey(String secret) {
        return Keys.hmacShaKeyFor(
                jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8));
    }
}
