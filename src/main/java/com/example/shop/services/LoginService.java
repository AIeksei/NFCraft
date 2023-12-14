package com.example.shop.services;

import com.example.shop.repositories.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private final LoginRepository loginRepository;
    public void saveLogin(Long number, String password) {
        loginRepository.saveLogin(number, password);
    }

    public void saveUser(String addres,String email,String first_name,String sur_name,
                         String father_name,Long number) {
        loginRepository.saveUser(email, first_name,sur_name,father_name,number);


    }
}
