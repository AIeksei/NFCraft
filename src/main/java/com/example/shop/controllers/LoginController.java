package com.example.shop.controllers;
import com.example.shop.models.Login;
import com.example.shop.models.Role;
import com.example.shop.repositories.LoginRepository;
import com.example.shop.services.LoginService;
import org.hibernate.boot.model.internal.XMLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    LoginRepository loginRepository;

    @GetMapping("/login")
    public String getLogin(){
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

//    @PostMapping("/registration")
//    public String postLogin(@RequestParam int number){
//        Optional<Login> login = loginRepository.findByNumber((long) number);
//        System.out.println(login.toString() + " for check ");
//        return "/catalog";
//    }
    @PostMapping("/registration")
    public String addLogin(@RequestParam(defaultValue = "123") String username, @RequestParam(defaultValue = "123") String password){
        System.out.println(9);
        Login userDb = loginRepository.findByLogin(username );
        if (userDb != null || password.length() == 0) {
            return "registration";
        }

        Role role = Role.USER;
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String pas = bCryptPasswordEncoder.encode(password);
            loginRepository.saveLogin(pas, username,true);
            Long id = loginRepository.findPasswordByEmail(username);
            loginRepository.saveRole(id, role.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("/user")
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
