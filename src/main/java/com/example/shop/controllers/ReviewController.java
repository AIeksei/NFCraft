package com.example.shop.controllers;
import com.example.shop.repositories.ReviewRepository;
import com.example.shop.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @Autowired
    ReviewRepository rewiewRepository;
    @Autowired
    ReviewService reviewService;

    @PostMapping("/rewiew/add")
    public ResponseEntity<String> rewiewAdd( @RequestParam String text,
                                             @RequestParam Float star,
                                             @RequestParam Long product_id,
                                             @RequestParam Long user_id)
    {
        try {
            System.out.println(6);
            rewiewRepository.saveReview(text, star, product_id, user_id);
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(7);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

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
