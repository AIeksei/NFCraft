package com.example.shop.services;


import com.example.shop.models.DTO.ProductDto;
import com.example.shop.models.Product;
import com.example.shop.repositories.CategoryRepository;
import com.example.shop.repositories.ProductRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public List<ProductDto> allProducts() {
        return productRepository.listProducts();
    }

    public List<ProductDto> findByIdProducts(Long newID) {
        return productRepository.cardProduct(newID);
    }

    public List<ProductDto> awgReviewStar(Long newID) {
        return productRepository.awgReviewStar(newID);
    }
    public List<ProductDto> ReviewStar(Long newID) {
        return productRepository.ReviewStar(newID);
    }
    public List<ProductDto> ReviewAllStar() {
        return productRepository.ReviewAllStar();
    }
    public void saveRewiews(String text, Float star, Long product_id,
                            Long user_id) {
        productRepository.saveRewiew(text, star, product_id, user_id);
    }
//    public void deleteProductAndReviews(String productName) {
//        productRepository.deleteProductAndReviewsByProductName(productName);
//    }
}