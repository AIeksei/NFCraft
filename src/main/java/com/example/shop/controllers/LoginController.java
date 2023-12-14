package com.example.shop.controllers;

import com.example.shop.models.Login;
import com.example.shop.repositories.LoginRepository;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.services.LoginService;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired LoginRepository loginRepository;

    @GetMapping("/login")
    public String getLogin(){
        System.out.println("start");
        Optional<Login> login = loginRepository.findByNumber(78L);
        System.out.println(login.toString());
        return "login";
    }

    /*@PostMapping("/login")
    public ResponseEntity<String> LoginAdd(@RequestParam Long number,@RequestParam String password)//@RequestParam Long number,
                                           // @RequestParam String password)
    {
        try {
            Optional<Login> login = loginRepository.findByNumber(number);
            System.out.println(login.toString());
            System.out.println(6);
            loginService.saveLogin(number, password);
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(7);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping("/login")
    public String postLogin(@RequestParam int number){
        Optional<Login> login = loginRepository.findByNumber((long) number);
        System.out.println(login.toString() + " for check ");
        return "/login";
    }

    @PostMapping("/registration")
    public ResponseEntity<String> UserAdd(@RequestParam String addres,
                                          @RequestParam String email,
                                          @RequestParam String first_name,
                                          @RequestParam String sur_name,
                                          @RequestParam String father_name,
                                          @RequestParam Long number)
    {
        try {
            System.out.println(6);
            loginService.saveUser(addres,email,first_name,sur_name,father_name,number);
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(7);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
