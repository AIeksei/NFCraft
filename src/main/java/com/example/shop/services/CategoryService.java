package com.example.shop.services;

import com.example.shop.models.DTO.CategoryDTO;
import com.example.shop.models.DTO.ProductDto;
import com.example.shop.repositories.CategoryRepository;
import com.example.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;
    public List<ProductDto> allCategory() {return categoryRepository.listCategory();}
    public void updateAndDeleteCategory(Long categoryId) {
        categoryRepository.updateAndDeleteCategory(categoryId);
    }
}
