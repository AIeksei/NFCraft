package com.example.shop.controllers;
import com.example.shop.models.DTO.PostReviewDTO;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.repositories.LoginRepository;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.repositories.ReviewRepository;
import com.example.shop.repositories.UserRepository;
import com.example.shop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewService reviewService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/review/add")
    public ResponseEntity<String> reviewAdd( @RequestBody PostReviewDTO postReviewDTO )
    {
        try {
            reviewRepository.saveReview2(LocalDate.now(),postReviewDTO.getStar(),postReviewDTO.getText(),postReviewDTO.getProduct_id(),loginRepository.findByLogin(postReviewDTO.getUser_id()));
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/allreview")
   public ResponseEntity<?> allreview() {
        return ResponseEntity.ok( reviewRepository.ReviewAllStar());
    }

    @GetMapping("/allreview/{star}")
    public List<ProductDto> getReviewsByStar(@PathVariable Integer star) {
        return reviewRepository.findReviewsByStar(star);
    }

    @GetMapping("/allreview/ASC")
    public ResponseEntity<List<ProductDto>> getReviewsASC() {
        List<ProductDto> reviews = reviewRepository.getReviewsASC();
        return ResponseEntity.ok(reviews);
    }
    @GetMapping("/allreview/DESC")
    public ResponseEntity<List<ProductDto>> getReviewsDESC() {
        List<ProductDto> reviews = reviewRepository.getReviewsDESC();
        return ResponseEntity.ok(reviews);
    }

   @DeleteMapping("review/delete/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
  }
}
