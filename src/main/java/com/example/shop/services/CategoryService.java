package com.example.shop.services;

import com.example.shop.repositories.CategoryRepository;
import com.example.shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public void updateAndDeleteCategory(Long categoryId) {
        categoryRepository.updateAndDeleteCategory(categoryId);
    }
}
