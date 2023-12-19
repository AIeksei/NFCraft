package com.example.shop.controllers;
import com.example.shop.models.AuthResponse;
import com.example.shop.models.DTO.LoginRequest;
import com.example.shop.models.DTO.PostUserDTO;
import com.example.shop.models.DTO.RegisterDTO;
import com.example.shop.models.Login;
import com.example.shop.repositories.LoginRepository;
import com.example.shop.services.LoginService;
import com.example.shop.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/registration")
    public AuthResponse register(@RequestBody RegisterDTO registerDTO) {
        Login savedLogin = loginService.saveLogin(Login.from(registerDTO, passwordEncoder));
        return authService.generateToken(savedLogin);
    }

    @GetMapping("/user/login/{login_id}")
    public ResponseEntity<Long> findByLogin(@PathVariable("login_id") Long login_id) {
        System.out.println(login_id);
        Long userId = loginRepository.findByLogin(login_id);
        System.out.println(login_id);
        System.out.println(userId);
        if (userId != null) {
            return ResponseEntity.ok(userId);
        } else {
            return ResponseEntity.ok(0l);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<String> UserAdd(@RequestBody PostUserDTO postUserDTO)
    {
        try {
            loginService.saveUser(postUserDTO.getAddres(),postUserDTO.getFather_name(),postUserDTO.getFirst_name(),postUserDTO.getPhone(),postUserDTO.getSur_name(),postUserDTO.getLogin_id());
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(7);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
