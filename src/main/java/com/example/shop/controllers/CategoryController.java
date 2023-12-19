package com.example.shop.controllers;

import com.example.shop.models.DTO.PostProductDTO;
import com.example.shop.models.DTO.PostReviewDTO;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.services.CategoryService;
import com.example.shop.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.shop.models.Category;
import com.example.shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> category(Model model ){
        System.out.println(categoryService.allCategory());
        return ResponseEntity.ok( categoryService.allCategory());

    }
    @PostMapping("/category/add")
    public ResponseEntity<String> saveCategory( @RequestBody PostProductDTO postProductDTO )
    {
        try {
            categoryRepository.saveCategory(postProductDTO.getCategory_name());
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/category/deleted/{categoryId}")
    public void updateAndDeleteCategory(@PathVariable Long categoryId) {
        System.out.println(categoryId);
        categoryRepository.updateAndDeleteCategory(categoryId);
    }
    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> listProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDto> products = categoryRepository.listProductsByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
