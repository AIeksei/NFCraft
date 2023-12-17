package com.example.shop.services.auth;

import com.example.shop.models.AuthResponse;
import com.example.shop.models.DTO.LoginRequest;
import com.example.shop.models.Login;
import com.example.shop.services.LoginService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final LoginService loginService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(@NonNull LoginRequest loginRequest) {
        final Login login = loginService.findByEmail(loginRequest.getEmail());
        if (passwordEncoder.matches(loginRequest.getPassword(), login.getPassword())) {
            return generateToken(login);
        } else {
            throw new RuntimeException("Неверный пароль");
        }
    }

    public AuthResponse generateToken(Login login) {
        final var accessToken = jwtService.generateAccessToken(login);
        return new AuthResponse(login.getId(), accessToken);
    }

}
