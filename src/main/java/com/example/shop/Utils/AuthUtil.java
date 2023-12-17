package com.example.shop.Utils;

import com.example.shop.models.JwtAuthentication;
import com.example.shop.models.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthUtil {

    public static JwtAuthentication generateAuth(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRole(getRole(claims));
        jwtInfoToken.setEmail(claims.getSubject());
        return jwtInfoToken;
    }

    private static Role getRole(Claims claims) {
        final String role = claims.get("role", String.class);
        return Role.valueOf(role);
    }

}