package com.example.shop.controllers;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @GetMapping("/catalog")
    public ResponseEntity<?> catalog(Model model ){
        return ResponseEntity.ok( productService.allProducts());

    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> productid(@PathVariable Long id) {
        return ResponseEntity.ok( productService.findByIdProducts(id));
    }

    @GetMapping("/avgreview/{id}")
    public ResponseEntity<?> avgreview(@PathVariable Long id) {
        return ResponseEntity.ok( productService.awgReviewStar(id));
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> greview(@PathVariable Long id) {
        return ResponseEntity.ok( productService.ReviewStar(id));
    }
//    @PostMapping("/organization/add")
//    public String orgAdd(@RequestParam String name,
//                         @RequestParam String mail,
//                         @RequestParam Long phone,
//                         @RequestParam String urlForImg
//    ) {
//        organizationRepository.saveCategory(name,adress,mail,phone,urlForImg);
//        return "redirect:/organizations";
//    }

}

