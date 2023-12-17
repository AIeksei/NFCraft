package com.example.shop.services.auth;

import com.example.shop.models.Login;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.OffsetDateTime;
import java.util.Date;


@Slf4j
@Service
public class JwtService {
    private final SecretKey jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode("vgxNw/GLVTpTb1oXUQXEeH/BmXBG+ovuzOXz0C4AZ5plrkx9ZrIHohI32+y0dcX7Hni6kv+NTJS+wO0984LIBA=="));

    public String generateAccessToken(@NonNull Login login) {
        final var odt = OffsetDateTime.now().plusMonths(1);
        final Date accessExpiration = Date.from(odt.toInstant());
        return Jwts.builder()
                .subject(login.getEmail())
                .expiration(accessExpiration)
                .signWith(jwtAccessSecret)
                .claim("role", login.getRole())
                .compact();
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    private boolean validateToken(@NonNull String token, @NonNull SecretKey secret) {
        try {
            Jwts.parser()
                    .verifyWith(secret)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getAccessClaims(@NonNull String token) {
        return getClaims(token, jwtAccessSecret);
    }

    private Claims getClaims(@NonNull String token, @NonNull SecretKey secret) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
