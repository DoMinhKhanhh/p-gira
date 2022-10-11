package com.backendjava18.pgira.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {
    private final String secretKey = "secretKeysecretKeysecretKeysecretKeysecretKey";
    private final String prefix = "Bearer ";
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    public String generateJwt(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .signWith(
                        Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))
                        , SignatureAlgorithm.HS256
                )
                .compact();
    }

    public String getToken(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization");

        if (jwt == null)
            return null;

        return jwt.substring(prefix.length());
    }

    public boolean validateJwt(String token) {
        log.info("Token request: " + token);

        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (UnsupportedJwtException e1) {
            log.error("JWT Token is not supported: ", e1);
        } catch (MalformedJwtException e2) {
            log.error("Invalid Token: ", e2);
        } catch (SignatureException e3) {
            log.error("Invalid signature: ", e3);
        } catch (ExpiredJwtException e4) {
            log.error("JWT is expired: ", e4);
        } catch (IllegalArgumentException e5) {
            log.error("JWT Claims is empty: ", e5);
        }

        return false;

    }

    public String getUsername(String token) {
        if (!validateJwt(token))
            return null;

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY).build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }
}
