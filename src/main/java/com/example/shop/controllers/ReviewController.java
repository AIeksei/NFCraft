package com.example.shop.controllers;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ReviewController {
    @Autowired
    private ProductService productService;
    @PostMapping("/rewiew/add")
    public ResponseEntity<String> rewiewAdd( @RequestParam String text,
                                             @RequestParam Float star,
                                             @RequestParam Long product_id,
                                             @RequestParam Long user_id)
    {
        try {
            System.out.println(6);
            productService.saveRewiews(text, star, product_id, user_id);
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            System.out.println(7);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allreview/{id}")
    public ResponseEntity<?> allreview() {
        return ResponseEntity.ok( productService.ReviewAllStar());
    }
}
