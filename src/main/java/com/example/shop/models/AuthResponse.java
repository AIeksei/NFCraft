package com.example.shop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
    private Integer loginId;
    private String accessToken;
}
