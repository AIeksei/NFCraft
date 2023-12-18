package com.example.shop.controllers;
import com.example.shop.models.AuthResponse;
import com.example.shop.models.DTO.LoginRequest;
import com.example.shop.models.DTO.RegisterDTO;
import com.example.shop.models.Login;
import com.example.shop.models.Role;
import com.example.shop.repositories.LoginRepository;
import com.example.shop.services.LoginService;
import com.example.shop.services.auth.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public AuthResponse login(@RequestBody LoginRequest authRequest) {
        return authService.login(authRequest);
    }

//    @PostMapping("/registration")
//    public String postLogin(@RequestParam int number){
//        Optional<Login> login = loginRepository.findByNumber((long) number);
//        System.out.println(login.toString() + " for check ");
//        return "/catalog";
//    }
    @PostMapping("/registration")
    public AuthResponse register(@RequestBody RegisterDTO registerDTO) {
        Login savedLogin = loginService.saveLogin(Login.from(registerDTO, passwordEncoder));
        return authService.generateToken(savedLogin);
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
