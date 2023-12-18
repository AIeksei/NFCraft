package com.example.shop.controllers;
import com.example.shop.models.DTO.PostReviewDTO;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.repositories.ReviewRepository;
import com.example.shop.repositories.UserRepository;
import com.example.shop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@CrossOrigin
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/review/add")
    public ResponseEntity<String> reviewAdd( @RequestBody PostReviewDTO postReviewDTO )
    {
        try {
            reviewRepository.saveReview2(LocalDate.now(),postReviewDTO.getStar(),postReviewDTO.getText(),postReviewDTO.getProduct_id(),postReviewDTO.getUser_id());
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

//    public AuthResponse register(@RequestBody RegisterDTO registerDTO) {
//        Login savedLogin = loginService.saveLogin(Login.from(registerDTO, passwordEncoder));
//        return authService.generateToken(savedLogin);
//    }
//    @GetMapping("/allreview/{id}")
//    public ResponseEntity<?> allreview() {
//        return ResponseEntity.ok( rewiewRepository.ReviewAllStar());
//    }

//    @DeleteMapping("/{reviewId}")
//    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
//        reviewService.deleteReviewById(reviewId);
//        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
//    }
}
