package com.example.shop.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.shop.models.Category;
import com.example.shop.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public  String category(Model model){
//        Iterable < Category> categories = categoryRepository.findAll();
//        model.addAttribute("categories", categories);
        return "category";
    }

//    @PostMapping("/category")
//    public String categoryAdd(@RequestParam String name, Model model){
//        Category category = new Category(name);
//        categoryRepository.save(category);
//        return "redirect:/category";
//    }
}
