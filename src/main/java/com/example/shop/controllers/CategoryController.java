package com.example.shop.controllers;

import com.example.shop.models.DTO.PostProductDTO;
import com.example.shop.models.DTO.PostReviewDTO;
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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> category(Model model ){
        System.out.println("as");
        System.out.println(categoryService.allCategory());
        System.out.println("ss");
        return ResponseEntity.ok( categoryService.allCategory());

    }
    @PostMapping("/category/add")
    public ResponseEntity<String> reviewAdd( @RequestBody PostProductDTO postProductDTO )
    {
        try {
            categoryRepository.saveCategory(postProductDTO.getCategory_name());
            return new ResponseEntity<>("Сохранено", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategoryAndMoveProducts(@PathVariable Long categoryId) {
        System.out.println(categoryId);
        categoryRepository.updateAndDeleteCategory(categoryId);
    }

//    @PostMapping("/category")
//    public String categoryAdd(@RequestParam String name, Model model){
//        Category category = new Category(name);
//        categoryRepository.save(category);
//        return "redirect:/category";
//    }
}
