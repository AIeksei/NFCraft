package com.example.shop.controllers;
import com.example.shop.models.DTO.PostProductDTO;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.DTO.ProductImageDTO;
import com.example.shop.repositories.ProductRepository;
import com.example.shop.services.ProductService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @GetMapping("/catalog")
    public ResponseEntity<?> catalog(Model model) {
        return ResponseEntity.ok(productService.allProducts());

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> productid(@PathVariable Long id) {
        if (productService.findByIdProducts(id).isEmpty()) {
            return new ResponseEntity<>("Ошибка ", HttpStatus.BAD_REQUEST);
        } else
            return ResponseEntity.ok(productService.findByIdProducts(id));
    }

    @GetMapping("/avgreview/{id}")
    public ResponseEntity<?> avgreview(@PathVariable Long id) {
        return ResponseEntity.ok(productService.awgReviewStar(id));
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<?> greview(@PathVariable Long id) {
        return ResponseEntity.ok(productService.ReviewStar(id));
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> reviewAdd(@RequestBody PostProductDTO postProductDTO) {
        logger.info("Добавление нового продукта: {}", postProductDTO.getName());
        try {
            productRepository.saveProduct(postProductDTO.getInfo(), postProductDTO.getName(), postProductDTO.getPrice(),
                    postProductDTO.getQuant(), postProductDTO.getCategory_id(), false);
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Ошибка при добавлении продукта", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/products/delete/{productId}")
    public String markProductAsDeleted(@PathVariable Long productId) {
        productService.markProductAsDeleted(productId);
        return "Product with ID " + productId + " has been marked as deleted.";
    }

    @PatchMapping("/products/patch/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody PostProductDTO postProductDTO) {

        productService.updateProductInfo(productId, postProductDTO.getInfo(), postProductDTO.getName(),
                postProductDTO.getPrice(), postProductDTO.getQuant(), postProductDTO.getCategory_id());
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @PostMapping("/image/add")
    public ResponseEntity<String> saveProductImage(@RequestBody ProductImageDTO imageDTO) {
        try {
            productRepository.saveImage(imageDTO.getCount(), imageDTO.getUrl(), imageDTO.getProduct_id());
            return new ResponseEntity<>("Product image saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save product image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/image/{id}")
    public ResponseEntity<List<ProductDto>> getAllImages(@PathVariable("id") Long productId) {
        List<ProductDto> images = productRepository.allImage(productId);
        return ResponseEntity.ok(images);
    }
}
