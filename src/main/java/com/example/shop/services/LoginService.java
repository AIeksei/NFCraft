package com.example.shop.services;

import com.example.shop.models.Login;
import com.example.shop.repositories.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private final LoginRepository loginRepository;

    public Login findByEmail(String email) {
        return loginRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public Login saveLogin(Login login) {
        Optional<Login> loginOptional = loginRepository.findByEmail(login.getEmail());
        if (loginOptional.isPresent()) {
            throw new RuntimeException("Пользователь уже существует");
        }
        loginRepository.saveLogin(login);
        return findByEmail(login.getEmail());
    }

    public void saveUser(String addres,String email,String first_name,String sur_name,
                         String father_name,Long number) {
        loginRepository.saveUser(email, first_name,sur_name,father_name,number);
    }

    public Login getCurrentLogin() {
        return findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
