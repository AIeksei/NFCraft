package com.example.shop.controllers;
import com.example.shop.models.DTO.PostProductDTO;
import com.example.shop.models.DTO.PostReviewDTO;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin
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

    @PostMapping("/product/add")
    public ResponseEntity<String> reviewAdd( @RequestBody PostProductDTO postProductDTO )
    {
        try {
            productRepository.saveProduct(postProductDTO.getInfo(),postProductDTO.getName(),postProductDTO.getPrice(),postProductDTO.getQuant(),postProductDTO.getCategory_id());
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
//    @PutMapping("/{productId}")
//    public ResponseEntity<String> updateProduct(
//            @PathVariable Long productId,
//            @RequestParam String info,
//            @RequestParam String name,
//            @RequestParam Float price,
//            @RequestParam Integer quantity,
//            @RequestParam Long categoryId) {
//
//        productService.updateProductInfo(productId, info, name, price, quantity, categoryId);
//        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
//    }

//    @DeleteMapping("/{productName}")
//    public ResponseEntity<String> deleteProduct(
//            @PathVariable String productName) {
//
//        productService.deleteProductByProductName(productName);
//        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
//    }

//    @PostMapping("/create")
//    public ResponseEntity<String> createProduct(
//            @RequestParam String info,
//            @RequestParam String name,
//            @RequestParam Float price,
//            @RequestParam Long quant,
//            @RequestParam Long categoryId) {
//
//        productService.saveProduct(info, name, price, quant, categoryId);
//        return new ResponseEntity<>("Product created successfully", HttpStatus.CREATED);
//    }


}

